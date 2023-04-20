package ru.hoprik.storymod;

import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatMessageContent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.profiling.jfr.event.PacketReceivedEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.Logger;
import org.stringtemplate.v4.ST;
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Executer;
import ru.hoprik.storymod.Engine.Hero;
import ru.hoprik.storymod.Engine.StoryFunction;

import java.util.Objects;


@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class dddd {
    static Player player;
    static Dialog dialog = new Dialog("Привет я нечто то заспавнит моба. Хочешь?", new Bench[]{new Bench("Да", new Dialog(1, ()-> StoryMod.logger.info(1))),
            new Bench("Нет", new Dialog(2, ()-> StoryMod.logger.info(2)))} );


    @SubscribeEvent
    public static void te(BlockEvent.BreakEvent event) {
        StoryFunction.Message(event.getPlayer(), "gg", "ddd");
        Executer executer = new Executer();
        executer.addS(() -> {
            player = event.getPlayer();
        }, 1);
        executer.addS(() -> {
            StoryFunction.Message(event.getPlayer(), "fgfgf", "thyhyuj");
        }, 1);
        executer.addS(() -> {
            dialog.show(event.getPlayer());
        }, 1);
        executer.addS(() -> {
            StoryFunction.Message(event.getPlayer(), "yyuyuyi", "fggggfgf");
        }, 1);
        executer.Exec();
    }
}