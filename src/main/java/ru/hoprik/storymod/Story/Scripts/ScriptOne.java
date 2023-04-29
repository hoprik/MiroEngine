package ru.hoprik.storymod.Story.Scripts;


import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.Config.StorySaveDataConfigBuilder;
import ru.hoprik.storymod.Story.Engine.Executer;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Network.Packets.SWakeUpPacket;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.Story.Engine.Bulidings.BuilderBuildings;
import ru.hoprik.storymod.StoryMod;


@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class ScriptOne {
    static boolean cooldown = false;

    @SubscribeEvent
    public static void buildwakeup(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player && !StorySaveDataConfigBuilder.IS_WAKE_UP.get()) {
            new BuilderBuildings(event.getLevel(), new BlockPos(8, -58, 8));
            StoryFunction.TeleportEntityAll((Player) event.getEntity(), new Vector3d(8 + 2.5D, -57, 8 + 2.5D));
            Executer executer = new Executer();
            executer.addS(() -> {
            }, 1);
            executer.addS(() -> {
                Network.sendToPlayer(new SWakeUpPacket(), (Player) event.getEntity());
            }, 1);
            executer.exec();
            StorySaveDataConfigBuilder.IS_WAKE_UP.set(true);
            StorySaveDataConfigBuilder.IS_WAKE_UP.save();
        }
    }



    @SubscribeEvent
    public static void intercat(PlayerInteractEvent.RightClickBlock event){
        if (event.getEntity().level.getBlockState(event.getHitVec().getBlockPos()).getBlock().defaultBlockState() == Blocks.WARPED_DOOR.defaultBlockState() && !cooldown){
            event.setCanceled(true);
            cooldown = true;
            Executer executer = new Executer();
            executer.addS(()-> StoryFunction.ShowWaitScreenAll(event.getEntity(), 3), 2);
            executer.addS(()-> StoryFunction.TeleportEntityAll(event.getEntity(), new Vector3d(8 + 2.5D, -50, 8 + 2.5D)), 2);
            executer.addS(()->cooldown = false, 1);
            executer.exec();
        }

    }


}