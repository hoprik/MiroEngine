package ru.hoprik.storymod.Engine.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableRunnable implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;

    // Fields
    private transient Thread thread;
    private Runnable runnable;

    // Constructor
    public SerializableRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.thread = new Thread(this);
    }

    // Methods
    public void run() {
        this.runnable.run();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.thread = new Thread(this);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
}
