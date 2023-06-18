package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.network;

public interface ISyncable {
    void onAnimationSync(int id, int state);

    default String getSyncKey() {
        return this.getClass().getName();
    }
}
