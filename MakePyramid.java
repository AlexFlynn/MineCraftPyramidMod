package com.codoptech;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class MakePyramid implements ICommand {
	private List aliases = new ArrayList();
	private Block block;
	
	public MakePyramid(){
		aliases.add("makepyramid");
	}
	
	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "makepyramid";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "makepyramid <blockmaterial> <x> <y> <z> <basesize>";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender s, String[] args) throws CommandException {
		
		//calling a validation routine that returns a boolean true/false
		if (!validArgs(s,args)) return; // block material type set if valid
		
		//declaring variables and assigning them values from the arguments
		int size = Integer.parseInt(args[4]);
		int xStart = Integer.parseInt(args[1]); int xEnd = xStart + size;
		int yStart = Integer.parseInt(args[2]); int yEnd = yStart + size;
		int zStart = Integer.parseInt(args[3]); int zEnd = zStart + size;
		
		//3 nested for loops to build the x,y,z data and then create a block at that location.
		//then incrementing and decrementing values in order to make the next level.
		for (int y = yStart; y < yEnd; y++)
		{
			for (int z = zStart; z < zEnd; z++)
			{
				for (int x = xStart; x < xEnd; x++)
				{
					((EntityPlayer)s).worldObj.setBlockState(new BlockPos(x,y,z), block.getBlockState().getBaseState());
				} // end for loop for x axis
			} // end for loop for z axis
			xStart++; xEnd--;
			zStart++; zEnd--;
			size--;
		} // end for loop for y axis
		
	} // end method processCommand

	//validating the arguments sent to processCommand
	//using logic process - errors return false and if all tests
	//pass then there is a return true at the end
	private boolean validArgs( ICommandSender s, String[] args) {
		String strErr = "";
		String ErrAxis = "";
		int argfail = 0;
		int testcoordinate = 0;
		
		//validate the number of arguments
		if (args.length != 5){
			SErr.s(s, "Arguments sent: "+args.length+" arguements required 5");
			return false;
		} // end if args.length
				
		if (!NumberUtils.isDigits(args[1]))
		{
			SErr.s(s, "The x axis coordinate: " + args[1] + " is invalid.");
			return false;
		}
		
		if (!NumberUtils.isDigits(args[2]))
		{
			SErr.s(s, "The y axis coordinate: " + args[2] + " is invalid.");
			return false;
		}
		
		if (!NumberUtils.isDigits(args[3]))
		{
			SErr.s(s, "The z axis coordinate: " + args[3] + " is invalid.");
			return false;
		}
		
		if (!NumberUtils.isDigits(args[4]))
		{
			SErr.s(s, "The size value: " + args[4] + " is invalid.");
			return false;
		}
		
		if (!(s instanceof EntityPlayer))
		{
			SErr.s(s, "The sender of the command: " + s.getClass().getName() + " and not type EntityPlayer.");
			return false;
		}
		
		if (!validBlock(s,args[0])) return false;
		
		//valid if we get to this point
		//block material set above
		
		return true;
	} // End validArgs method
	

	
	private boolean validBlock(ICommandSender s, String IsBlock){
		String strErr = "";
		
		strErr = "Argument sent for block type: "+IsBlock+" is not a valid block type or Block ID.";
		try 
		{
			//kind of want to extend the Block type with getBlock() - that takes either number
			//or name.  Maybe I'll do that next - this code is annoying.
			//kind of like entering in a search term or a web site.
			//it shouldn't be hard to figure out what the user sent
			if (Block.getBlockFromName(IsBlock) == null){
				if (Block.getBlockById(Integer.parseInt(IsBlock)) == null){
					SErr.s(s, strErr);
					return false;
				} 
				else // else if block...getBlockByID
				{
					block = Block.getBlockById(Integer.parseInt(IsBlock));
				} // end if block...getBlockByID
			} else // else if block...getBlockFromName
			{
					block = Block.getBlockFromName(IsBlock);
			} // end if block...getBlockFromName
			
		} catch (NumberFormatException e)
		{
			SErr.s(s, strErr);
			return false;
		}

		return true;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender instanceof EntityPlayer;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
