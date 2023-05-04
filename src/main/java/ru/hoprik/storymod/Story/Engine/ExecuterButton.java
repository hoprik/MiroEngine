package ru.hoprik.storymod.Story.Engine;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.StoryMod;

import java.util.ArrayList;
import java.util.List;
public class ExecuterButton {
    List<Action> callbacks = new ArrayList<>();
    Thread thread;
    public ExecuterButton(){
    }


    public void add(Runnable func){
        callbacks.add(new Action(func, 0));
    }
    public void exec(){
        new KeyBind(this);
    }
}

@Mod.EventBusSubscriber(modid = StoryMod.MODID, value = Dist.CLIENT)
class KeyBind{
    static ExecuterButton executer;
    static List<Action> callbacks;
    static int idAction = 0;

    public KeyBind(ExecuterButton executer){
        this.executer = executer;
        callbacks = executer.callbacks;
    }


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if(ru.hoprik.storymod.Story.Engine.Utils.KeyBind.NPC_DIALOG.consumeClick()) {
            try {
                callbacks.get(idAction).callback.run();
                idAction++;
            }catch (IndexOutOfBoundsException e){
                executer = null;
            }

        }
    }
}