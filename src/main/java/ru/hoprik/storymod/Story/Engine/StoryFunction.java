package ru.hoprik.storymod.Story.Engine;

import com.mojang.brigadier.ParseResults;
import com.mojang.math.Vector3d;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import ru.hoprik.storymod.Story.Engine.Mixins.ChatComponentMixin2;
import ru.hoprik.storymod.Story.Engine.MixinsInter.GuiMessageOwnerAccessor;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Network.Packets.*;
import ru.hoprik.storymod.Story.Engine.Utils.ChatHeads;
import ru.hoprik.storymod.Story.Engine.Utils.SerializableRunnable;
import ru.hoprik.storymod.StoryMod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class StoryFunction implements Serializable{

    public static void Message(Player playerAll, String hero, String text, ResourceLocation location){
        if (!playerAll.level.isClientSide) {
            for (Player player : playerAll.getServer().getPlayerList().getPlayers()) {

                if (location != null) {
                    ChatHeads.lastPlayerSkin = location;
                }
                else {
                    ChatHeads.lastPlayerSkin = new ResourceLocation("storymod", "textures/entity/player.png");
                }

                MutableComponent message = Component.empty();
                MutableComponent hero_comp = Component.literal("["+hero+"] ").withStyle(style -> style.withColor(Settings.colorChat));
                MutableComponent text_comp = Component.literal(text).withStyle(style -> style.withColor(ChatFormatting.WHITE));

                message.append(hero_comp);
                message.append(text_comp);

                player.sendSystemMessage(message);
            }
        }
        else {
            if (location != null) {
                Network.sendToServer(new SPlayerMessagePacketWithHeads(hero, text, location));
            }
            else {
                Network.sendToServer(new SPlayerMessagePacketWithHeads(hero, text, new ResourceLocation("storymod", "textures/entity/player.png")));
            }

        }
    }
    public static void Message(Player playerAll, String hero, String text){
        if (!playerAll.level.isClientSide) {
            for (Player player : playerAll.getServer().getPlayerList().getPlayers()) {
                //player.sendSystemMessage(Component.literal(ChatFormatting.DARK_PURPLE + "[" + hero + "]" + ChatFormatting.RESET + " " + text).withStyle(style -> style.withColor(16757663)));
                MutableComponent message = Component.empty();
                MutableComponent hero_comp = Component.literal("["+hero+"] ").withStyle(style -> style.withColor(Settings.colorChat).withFont(Settings.fontHero));
                MutableComponent text_comp = Component.literal(text).withStyle(style -> style.withColor(ChatFormatting.WHITE).withFont(Settings.fontText));

                message.append(hero_comp);
                message.append(text_comp);

                player.sendSystemMessage(message);
            }
        }
        else {
            Network.sendToServer(new SPlayerMessagePacket(hero, text));
        }
    }

    public static void MessageFormOnePlayer(Player player, String hero, String text){
        MutableComponent message = Component.empty();
        MutableComponent hero_comp = Component.literal("["+hero+"] ").withStyle(style -> style.withColor(Settings.colorChat).withFont(Settings.fontHero));
        MutableComponent text_comp = Component.literal(text).withStyle(style -> style.withColor(ChatFormatting.WHITE).withFont(Settings.fontText));

        message.append(hero_comp);
        message.append(text_comp);

        player.sendSystemMessage(message);
    }

    public static void unlockRecipe(Player player, String nameCraft) {
        Collection<Recipe<?>> recipes = new ArrayList<>();
        if (!player.level.isClientSide) {
            for (Recipe<?> recipe : player.getServer().getRecipeManager().getRecipes()) {
                if (recipe.getId().toString().equals(nameCraft)) {
                    recipes.add(recipe);
                }
            }
            player.awardRecipes(recipes);
        }
    }

    public static void ShowWaitScreenAll(Player player, int text){
        if (!player.level.isClientSide) {
            for (ServerPlayer human : player.getServer().getPlayerList().getPlayers()) {
                Network.sendToPlayer(new SWaitScreenPacket(text), human);
            }
        }
        else {
            Network.sendToServer(new SWaitScreenServerPacket(text));
        }
    }
    public static void TeleportEntityAll(Player player, Vector3d vector3d){
        if (!player.level.isClientSide) {
            Level world = player.level;
            for (double y = vector3d.y; y <= 255; y++) {
                if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.defaultBlockState()) {
                    for (ServerPlayer serverPlayerEntity : player.getServer().getPlayerList().getPlayers()) {
                        StoryMod.logger.info("her");
                        serverPlayerEntity.teleportTo((ServerLevel) world, vector3d.x, y, vector3d.z, player.getYRot(), player.getXRot());
                    }
                    break;
                }
            }
        }
        else {
            Network.sendToServer(new STeleportPlayer(vector3d));
        }
    }

    public static void openTrade(){

    }

    public static void executeCommand(Player player, String command) {
        player.getServer().getCommands().performPrefixedCommand(player.createCommandSourceStack(), "/" + command);
    }


}
