package ru.hoprik.storymod.Story.Scripts;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.Entity.SeatEntity;
import ru.hoprik.storymod.Init.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.Executer;
import ru.hoprik.storymod.Story.Engine.ExecuterButton;
import ru.hoprik.storymod.Story.Engine.Gui.WakeUpScreen;
import ru.hoprik.storymod.Story.Engine.Hero.Hero;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Network.Packets.SWakeUpPacket;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class Script {
    static Executer station = new Executer();


    @SubscribeEvent
    public static void ScriptCall(EntityJoinLevelEvent event){
        if (!event.getLevel().isClientSide && event.getEntity() instanceof Player){
            Hero ps1 = new Hero(new NpcEntity(InitEntity.PASSAGER1.get(), event.getLevel()), new BlockPos(event.getEntity().getX()+2, event.getEntity().getY(), event.getEntity().getZ()+2));
            Hero ps2 = new Hero(new NpcEntity(InitEntity.PASSAGER1.get(), event.getLevel()), new BlockPos(event.getEntity().getX()-2, event.getEntity().getY(), event.getEntity().getZ()+2));
            Hero ps3 = new Hero(new NpcEntity(InitEntity.PASSAGER1.get(), event.getLevel()), new BlockPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ()+2));

            ps1.setEmote("story.npc.angry");




            Executer screen = new Executer();
            screen.addNullTaskS(1);
            screen.addS(()-> Network.sendToPlayer(new SWakeUpPacket(), (Player) event.getEntity()), 1);
            screen.addS(()->SeatEntity.create(event.getLevel(), new BlockPos(1,1,1), 0.4f, (Player) event.getEntity(), Direction.SOUTH), 3);
            screen.exec();
        }
    }

    @SubscribeEvent
    public static void ScriptStart(ScreenEvent.MouseButtonPressed.Post event){
        if (event.getScreen() instanceof WakeUpScreen){
            station.addNullTaskS(10);
            station.addS(()->StoryFunction.ShowWaitScreenAll(event.getScreen().getMinecraft().player, 3),1);
            station.addS(()->StoryFunction.TeleportEntityAll(event.getScreen().getMinecraft().player, new Vector3d(50, event.getScreen().getMinecraft().player.getY(), 50)), 1);
            station.exec();
        }
    }
}
