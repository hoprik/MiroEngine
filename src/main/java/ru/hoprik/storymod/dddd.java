package ru.hoprik.storymod;


import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Executer;
import ru.hoprik.storymod.Engine.Hero.Hero;
import ru.hoprik.storymod.Engine.StoryFunction;
import ru.hoprik.storymod.Engine.Utils.BuilderBuildings;

import java.io.Serializable;


@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class dddd {
    static Player player;
    static Dialog dialog = new Dialog("Привет я нечто то заспавнит моба. Хочешь?", new Bench[]{
            new Bench("Да",
                    new Dialog(1, (Serializable & Runnable) ()->{
                        yes();
                    })),
            new Bench("Нет",
                    new Dialog(2, (Serializable & Runnable) () -> {StoryMod.logger.info("it`s working 2");}))});


    @SubscribeEvent
    public static void te(BlockEvent.BreakEvent event) {
        Executer executer = new Executer();
        BuilderBuildings builderBuildings = new BuilderBuildings(event.getPlayer().level, new BlockPos(8, -58, 8));
        executer.addS(() -> {
            player = event.getPlayer();
        }, 1);
        executer.addS(() -> {
            StoryFunction.Message(event.getPlayer(), "бен", "1234");
        }, 1);
        executer.addS(() -> {
            dialog.show(event.getPlayer());
        }, 5);
        executer.Exec();
    }

    public static void yes(){
        Executer executer = new Executer();
        Hero hero = new Hero(new Sheep(EntityType.SHEEP, player.level), new BlockPos(0,-60, 0));;
        executer.addS(()->{
            hero.moveEntity(new Vector3d(8,-60, 8), 10);
        }, 5);
        executer.addS(()->{
            hero.stopMoveEntity();
        }, 5);
        executer.Exec();

    }
}