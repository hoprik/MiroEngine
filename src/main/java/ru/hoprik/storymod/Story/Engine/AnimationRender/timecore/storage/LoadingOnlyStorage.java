package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.storage;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.GlobalResourceStorage;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.TimeResourceHolder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder.Temporal;

import java.util.ArrayList;
import java.util.List;

public class LoadingOnlyStorage {
    private static final Temporal<List<TimeResourceHolder>> HOLDERS = Temporal.of(new ArrayList<>(), "Holders were already loaded. You need to add it earlier!");

    public synchronized static void addResourceHolder(TimeResourceHolder holder) {
        HOLDERS.get().add(holder);
    }

    public synchronized static void tryLoadResourceHolders() {
        HOLDERS.doAndRemove(GlobalResourceStorage.INSTANCE::fill);
    }
}
