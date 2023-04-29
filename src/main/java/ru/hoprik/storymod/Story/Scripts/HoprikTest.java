package ru.hoprik.storymod.Story.Scripts;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Story.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Story.Engine.Executer;
import ru.hoprik.storymod.Story.Engine.Hero.Hero;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

import java.io.Serializable;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class HoprikTest {
    static boolean cooldown = false;
    static Player player;
    static Dialog dialog = new Dialog("Привет я нечто то заспавнит моба. Хочешь?", new Bench[]{
            new Bench("Да",
                    new Dialog(1, (Serializable & Runnable) ()->{
                        yes();
                    })),
            new Bench("Нет",
                    new Dialog(2, (Serializable & Runnable) () -> {
                        no();
                    }))});
    @SubscribeEvent
    public static void Test(BlockEvent.BreakEvent event){
        player = event.getPlayer();
        dialog.show(event.getPlayer());
    }

    public static void yes(){
        Hero hero = new Hero(new NpcEntity(InitEntity.HOPRIK.get(), player.level), new BlockPos(0, -60, 0));
        hero.moveEntity(new Vector3d(9,-60,9), 0.4F);
    }

    public static void no(){
        Hero hero = new Hero(new NpcEntity(InitEntity.YBLEDOK.get(), player.level), new BlockPos(0, -60, 0));
        hero.moveEntity(new Vector3d(9,-60,9), 0.4F);
    }
    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof NpcEntity && !cooldown) {
            cooldown = true;
            Player player = event.getEntity();
            StoryFunction.Message(player, "ZenHunT", "Я гей!");
            Executer executer = new Executer();
            executer.addS(() -> dialog.show(player), 2);
            executer.addS(() -> cooldown = false, 2);
            executer.exec();
        }
    }
}
