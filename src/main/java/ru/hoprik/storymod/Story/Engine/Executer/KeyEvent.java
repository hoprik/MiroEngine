package ru.hoprik.storymod.Story.Engine.Executer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Story.Engine.Utils.KeyBinds;
import ru.hoprik.storymod.StoryMod;

import java.util.List;

@Mod.EventBusSubscriber(modid = StoryMod.MODID, value = Dist.CLIENT)
public class KeyEvent {
    static List<Action> callbacks;
    static Executer exec;
    static int idAction = 0;

    public static void KeyEvent(Executer executer, List<Action> action) {
        callbacks = action;
        exec = executer;
    }


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyBinds.NPC_DIALOG.consumeClick()) {
            try {
                if (idAction == callbacks.size()) {
                    StoryMod.logger.info("sssss555ss");
                    exec.exec();
                    exec.resume();
                    idAction = 0;
                }
                else {
                    callbacks.get(idAction).callback.run();
                    idAction++;
                }
            } catch (Exception e) {
            }

        }
    }
}
