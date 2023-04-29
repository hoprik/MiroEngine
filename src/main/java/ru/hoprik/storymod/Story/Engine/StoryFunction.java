package ru.hoprik.storymod.Story.Engine;


import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Network.Packets.*;
import ru.hoprik.storymod.StoryMod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class StoryFunction implements Serializable{
    public static void Message(PlayerEntity playerAll, String hero, String text){
        if (!playerAll.level.isClientSide) {
            for (PlayerEntity player : playerAll.getServer().getPlayerList().getPlayers()) {
                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "[" + hero + "]" + TextFormatting.RESET + " " + text), player.getUUID());
            }
        }
        else {
            Network.sendToServer(new SPlayerMessagePacket(hero, text));
        }
    }

    public static void MessageFromFirstPlayer(PlayerEntity player, String hero, String text){
        player.sendMessage(new StringTextComponent(TextFormatting.DARK_GREEN +"["+hero+"]"+TextFormatting.RESET+" "+text), player.getUUID());
    }

    public static void unlockRecipe(PlayerEntity player, String nameCraft) {
        Collection<IRecipe<?>> recipes = new ArrayList<>();
        if (!player.level.isClientSide) {
            for (IRecipe<?> recipe : player.getServer().getRecipeManager().getRecipes()) {
                if (recipe.getId().toString().equals(nameCraft)) {
                    recipes.add(recipe);
                }
            }
            player.awardRecipes(recipes);
        }
    }

    public static void ShowWaitScreenAll(PlayerEntity player, int text){
        if (!player.level.isClientSide) {
            for (ServerPlayerEntity human : player.getServer().getPlayerList().getPlayers()) {
                Network.sendToPlayer(new SWaitScreenPacket(text), human);
            }
        }
        else {
            Network.sendToServer(new SWaitScreenServerPacket(text));
        }
    }
    public static void TeleportEntityAll(PlayerEntity player, Vector3d vector3d){
        if (!player.level.isClientSide) {
            World world = player.level;
            for (double y = vector3d.y; y <= 255; y++) {
                if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.defaultBlockState()) {
                    for (ServerPlayerEntity serverPlayerEntity : player.getServer().getPlayerList().getPlayers()) {
                        StoryMod.logger.info("her");
                        serverPlayerEntity.teleportTo((ServerWorld) world, vector3d.x, y, vector3d.z, player.yRot, player.xRot);
                    }
                    break;
                }
            }
        }
        else {
            Network.sendToServer(new STeleportPlayer(vector3d));
        }
    }


}
