package com.codoptech;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SErr {

	//Send Error Message
	static void s(ICommandSender s, String msg) {
		s.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+msg));
	}
}
