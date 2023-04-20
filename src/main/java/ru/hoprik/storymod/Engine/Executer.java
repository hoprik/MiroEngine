package ru.hoprik.storymod.Engine;

import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.StoryMod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Executer {
    List<Action> callbacks = new ArrayList<>();
    boolean Run = true;
    int idAction = 0;
    Thread thread;
    public Executer(){
    }
    public void add(Runnable func, int millisecond){
        callbacks.add(new Action(func, millisecond));
    }
    public void addS(Runnable func, int second){
        callbacks.add(new Action(func, second*1000));
    }
    public void Stop(){
        this.Run = false;
    }
    public void Exec(){
        thread = new Thread(()-> {
            while (Run) {
                Action action = callbacks.get(idAction);
                action.callback.run();
                try {
                    Thread.sleep(action.timer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                idAction++;
                if (idAction == callbacks.size()) {
                    Run = false;
                    idAction = 0;
                    }
            }
        });
        thread.start();
    }
}
class Action{
    Runnable callback;
    int timer;
    public Action(Runnable callback, int timer){
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