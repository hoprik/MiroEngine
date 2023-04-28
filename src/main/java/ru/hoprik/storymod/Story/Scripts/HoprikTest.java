package ru.hoprik.storymod.Story.Scripts;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.Hero.Hero;
import ru.hoprik.storymod.StoryMod;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class HoprikTest {
    @SubscribeEvent
    public static void Test(BlockEvent.BreakEvent event){
        Hero hero = new Hero(new NpcEntity(InitEntity.HOPRIK.get(), event.getPlayer().level), new BlockPos(0, -60, 0));
        hero.moveEntity(new Vector3d(9,-60,9), 0.4F);
    }
}
