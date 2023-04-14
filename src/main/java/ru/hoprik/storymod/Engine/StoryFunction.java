package ru.hoprik.storymod.Engine;

import com.mojang.math.Vector3d;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.crafting.IShapedRecipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class StoryFunction {
    public static void Message(Player playerAll, String hero, String text){
        for (Player player: playerAll.getServer().getPlayerList().getPlayers()) {
            player.sendSystemMessage(Component.literal(ChatFormatting.DARK_GREEN +"["+hero+"]"+ChatFormatting.RESET+" "+text));
        }
    }

    public static void MessageFromFirstPlayer(Player player, String hero, String text){
        player.sendSystemMessage(Component.literal((ChatFormatting.DARK_GREEN +"["+hero+"]"+ChatFormatting.RESET+" "+text)));
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

    public static void ShowWaitScreenAll(Player player, String text){
        for (Player human: player.level.players()){
            human.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,  100, 255, false,false));
            for(ServerPlayer serverPlayerEntity: human.getServer().getPlayerList().getPlayers()){
                if (human.getName().toString().equals(serverPlayerEntity.getName().toString())) {
                    serverPlayerEntity.connection.send(new ClientboundSetTitleTextPacket(Component.literal(text)));
                }
            }
        }
    }
    public static void TeleportEntityAll(Player player, Vector3d vector3d){
        Level world = player.level;
        for (double y = vector3d.y; y<=255; y++){
            if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.defaultBlockState()){
                for(ServerPlayer serverPlayerEntity: player.getServer().getPlayerList().getPlayers()){
                    serverPlayerEntity.teleportTo(Objects.requireNonNull(player.level.getServer().getLevel(player.level.dimension())),vector3d.x, y, vector3d.z, player.getYRot(), player.getXRot() );
                }
                break;
            }
        }
    }

}
