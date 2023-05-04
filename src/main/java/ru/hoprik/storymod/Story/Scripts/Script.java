package ru.hoprik.storymod.Story.Scripts;

import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Story.Engine.Executer;
import ru.hoprik.storymod.Story.Engine.ExecuterButton;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class Script {
    @SubscribeEvent
    public static void BlockBreak(BlockEvent.BreakEvent event){
        ExecuterButton executer = new ExecuterButton();
        executer.add(()-> StoryFunction.Message(event.getPlayer(), "HER", "ХАЛОУ"));
        executer.add(()-> StoryFunction.Message(event.getPlayer(), "HER", "ХАЛОУ"));
        executer.add(()-> StoryFunction.Message(event.getPlayer(), "HER", "вав"));
        executer.add(()-> StoryFunction.Message(event.getPlayer(), "HER", "вава"));
        executer.add(()-> StoryFunction.Message(event.getPlayer(), "HER", "цуук"));
        Executer executer1 = new Executer();
        executer1.addS(()->StoryFunction.Message(event.getPlayer(), "АБОБА", "1"), 1);
        executer1.addS(()->StoryFunction.Message(event.getPlayer(), "АБОБА", "2"), 1);
        executer1.addS(()->StoryFunction.Message(event.getPlayer(), "АБОБА", "3"), 1);
        executer1.addS(()->executer.exec(), 1);
        executer1.exec();
    }
}
