package ru.hoprik.storymod.Story.Engine.Utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static final String KEY_CATEGORY_STORY = "key.category.storymod.story";
    public static final String KEY_NPC = "key.storymod.npc";

    public static final KeyMapping NPC_DIALOG = new KeyMapping(KEY_NPC, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_STORY);
}
