package ru.hoprik.storymod.Story.Engine.Executer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.StoryMod;

import java.util.ArrayList;
import java.util.List;
public class Executer {
    List<Action> callbacks = new ArrayList<>();
    List<List<Action>> callbacksButtons = new ArrayList<>();
    boolean run = true;
    boolean killThread = false;
    boolean isLastAddButton = false;
    int idAction = 0;
    int idActionButton = 0;
    Thread thread;
    public Executer(){
    }

    public void addNullTaskS(int second){
        isLastAddButton = false;
        callbacks.add(new Action(null, second*1000));
    }
    public void addNullTask(int millisecond){
        isLastAddButton = false;
        callbacks.add(new Action(null, millisecond));
    }
    public void add(Runnable func, int millisecond){
        isLastAddButton = false;
        callbacks.add(new Action(func, millisecond));
    }
    public void addS(Runnable func, int second){
        isLastAddButton = false;
        callbacks.add(new Action(func, second*1000));
    }
    public void addB(Runnable func){
        List<Action> actions;
        if (isLastAddButton) {
            actions = callbacksButtons.get(callbacksButtons.size() - 1);
            actions.add(new Action(func, 5 * 500));
        }
        else {
            actions = new ArrayList<>();
            actions.add(new Action(func, 5 * 500));
            callbacks.add(new Action(func, 5 * 500));
            callbacksButtons.add(actions);
        }
        isLastAddButton = true;
    }
    public void resume(){
        this.run = true;
    }
    public void suspend(){
        this.run = false;
    }
    public void stop(){
        this.killThread = true;
    }
    public void exec(){
        thread = new Thread(()-> {
            while (!killThread) {
                if (run) {
                    StoryMod.logger.info("sssssss4445");
                    Action action = callbacks.get(idAction);
                    StoryMod.logger.info(action.toString());
                    if (action.callback != null && action.timer != 5 * 500) {
                        action.callback.run();
                        idAction++;
                        try {
                            Thread.sleep(action.timer);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (action.timer == 5 * 500) {
                        KeyEvent.KeyEvent(this, callbacksButtons.get(idActionButton));
                        idActionButton++;
                        idAction++;
                        suspend();
                    }
                    else if (action.callback == null){
                        idAction++;
                        try {
                            Thread.sleep(action.timer);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (idAction == callbacks.size()) {
                        suspend();
                        stop();
                        idAction = 0;
                    }
                }
            }
        });
        thread.start();
    }
}
class Action {
    Runnable callback;
    int timer;

    public Action(Runnable callback, int timer) {
        this.callback = callback;
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "Action{" +
                "callback=" + callback +
                ", timer=" + timer +
                '}';
    }
}

