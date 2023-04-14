package ru.hoprik.storymod;

import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatMessageContent;
import net.minecraft.network.chat.Component;
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
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Executer;
import ru.hoprik.storymod.Engine.Hero;
import ru.hoprik.storymod.Engine.StoryFunction;


@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class dddd {
    static Player player;
    static Dialog dialog = new Dialog("Привет я нечто то заспавнит моба. Хочешь?", new Bench[]{new Bench("Да!", ()->{
        Minecraft mc = Minecraft.getInstance();
        Executer executer = new Executer();
        executer.addS(() -> mc.setScreen(null), 1);
        executer.addS(() -> {
            Hero hero = new Hero(new Sheep(EntityType.SHEEP, player.level), new BlockPos(0,-57,0));
            Executer executer2 = new Executer();
            executer2.addS(()-> {StoryFunction.Message(player, "Овца", "привет! Я иду на 5,5");
                hero.moveEntity(new Vector3d(5, -60, 5), 1);},3);
            executer2.addS(()-> {StoryFunction.Message(player, "Овца", "А знаешь я хочу к центру");
                hero.stopMoveEntity();},1);
            executer2.addS(()-> {StoryFunction.Message(player, "Овца", "Вот и иду");
                hero.moveEntity(new Vector3d(8, -60, 8), 1);},1);
            executer2.Exec();}, 1);
        executer.Exec();
    }), new Bench("Нет", ()->{

    })});
    @SubscribeEvent
    public static void te(BlockEvent.BreakEvent event){
        StoryFunction.Message(event.getPlayer(), "gg", "ddd");
        Executer executer = new Executer();
        executer.addS(() -> {player = event.getPlayer();}, 1);
        executer.addS(() -> {StoryFunction.Message(event.getPlayer(), "fgfgf", "thyhyuj");}, 1);
        executer.addS(()-> {dialog.show(event.getPlayer());}, 1);
        executer.Exec();
    }
}
