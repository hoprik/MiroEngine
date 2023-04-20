package ru.hoprik.storymod.Engine.Utils;

import java.io.Serializable;

public class SerializableRunnable implements Runnable, Serializable{
    Runnable runnable;
    public SerializableRunnable(Runnable runnable){
        this.runnable = runnable;
    }
    @Override
    public void run() {
        this.runnable.run();
    }
}
