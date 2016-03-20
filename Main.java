package com.codoptech;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
	public static final String MODID = "CodopTechMods";
	public static final String VERSION = "1.0";
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    // mod to register on the bus for event handling
	    //MinecraftForge.EVENT_BUS.register(new newMessage());
	    // end code to register the event for event handling
	    
	} // end init method
	
	@EventHandler
	public void regCustCommand(FMLServerStartingEvent event)
	{
		//sample register custom command statement where makeSomethingNew
		//is a class in the package
		//event.registerServerCommand(new SampleCommand());
		//end sample code
		
		event.registerServerCommand(new MakePyramid());
		
	} // end regCustCommand method
}
