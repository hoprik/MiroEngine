package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.base;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.TimeRegister;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder.Temporal;

import java.util.ArrayList;
import java.util.List;

public abstract class RunnableStoringRegister extends TimeRegister {
    private final Temporal<List<Runnable>> runnables = Temporal.of(new ArrayList<>());

    public RunnableStoringRegister(String modId) {
        super(modId);
    }

    protected void add(Runnable runnable) {
        runnables.get().add(runnable);
    }

    protected void runAll() {
        runnables.doAndRemove(runnables -> {
            for (Runnable task : runnables) {
                task.run();
            }
        });
    }
}
