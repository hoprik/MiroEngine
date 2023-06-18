package ru.hoprik.storymod.Story.Engine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import org.w3c.dom.Text;

public class Settings {
    public static TextColor colorChat = TextColor.parseColor("#7f3bcc");
    public static ResourceLocation fontHero = new ResourceLocation("minecraft","default");
    public static ResourceLocation fontText = new ResourceLocation("minecraft","default");
}
