package ru.hoprik.storymod.Story.Scripts;


import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
    public static void buildwakeup(EntityJoinWorldEvent event){
        if (event.getEntity() instanceof PlayerEntity && !StorySaveDataConfigBuilder.IS_WAKE_UP.get()) {
            new BuilderBuildings(event.getWorld(), new BlockPos(8, 8, 8));
            StoryFunction.TeleportEntityAll((PlayerEntity) event.getEntity(), new Vector3d(8 + 2.5D, 10, 8 + 2.5D));
            Executer executer = new Executer();
            executer.addS(() -> {
            }, 1);
            executer.addS(() -> {
                Network.sendToPlayer(new SWakeUpPacket(), (PlayerEntity) event.getEntity());
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
            executer.addS(()-> StoryFunction.ShowWaitScreenAll((PlayerEntity) event.getEntity(), 3), 2);
            executer.addS(()-> StoryFunction.TeleportEntityAll((PlayerEntity) event.getEntity(), new Vector3d(8 + 2.5D, -50, 8 + 2.5D)), 2);
            executer.addS(()->cooldown = false, 1);
            executer.exec();
        }

    }


}