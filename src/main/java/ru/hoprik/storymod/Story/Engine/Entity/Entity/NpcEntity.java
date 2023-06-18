package ru.hoprik.storymod.Story.Engine.Entity.Entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.IAnimatable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.PlayState;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.builder.AnimationBuilder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.controller.AnimationController;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.event.predicate.AnimationEvent;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationData;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationFactory;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.util.GeckoLibUtil;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationSystem;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.BlendType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.AnimationSystemBuilder;
import ru.hoprik.storymod.StoryMod;

public class NpcEntity extends Animal implements IAnimatable, AnimatedObject<NpcEntity> {

    private static final String LAYER_ANIMATION = "animation";
    private static final String LAYER_EMOTE = "emote";

    private static final EntityDataAccessor<Boolean> IS_LOOP =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<String> ANIMATION =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> EMOTE =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.STRING);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final AnimationSystem<NpcEntity> animationSystem;
    public NpcEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);

        animationSystem = AnimationSystemBuilder.forEntity(this, this.level, iAnimationManagerBuilder ->{
            iAnimationManagerBuilder.addLayer(LAYER_ANIMATION, BlendType.OVERWRITE, 1f);
            iAnimationManagerBuilder.addLayer(LAYER_EMOTE, BlendType.ADD, 1f);
        });
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6));
        super.registerGoals();
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f).build();
    }

    @Override
    public NpcEntity getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getAnimation() != "") {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(this.getAnimation()));
            animationSystem.getAnimationManager().setAnimation(new AnimationStarter(StoryMod.core.get(this.getAnimation())), "animation");
            return PlayState.CONTINUE;
        }

        animationSystem.getAnimationManager().setAnimation(new AnimationStarter(StoryMod.core.get("animation.npc.idle")), "animation");
        event.getController().setAnimation(new AnimationBuilder().addAnimation("story.npc.idle"));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState emote(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation((this.getEmote())));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controllerMove",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controllerEmote",
                0, this::emote));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.PLAYER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setAnimation(tag.getString("animation"), tag.getBoolean("isLoop"));
        setEmote(tag.getString("emote"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("animation", this.getAnimation());
        tag.putString("emote", this.getEmote());
        tag.putBoolean("isLoop", this.getLoop());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, "");
        this.entityData.define(EMOTE, "");
        this.entityData.define(IS_LOOP, false);
    }

    public void setAnimation(String animations, boolean isLoop) {
        this.entityData.set(ANIMATION, animations);
        this.entityData.set(IS_LOOP, isLoop);
    }

    public void setTimeCoreAnimation(AnimationStarter starter){
       animationSystem.getAnimationManager().setAnimation(starter, "animation");
    }

    public void setTimeCoreEmote(AnimationStarter starter){
        animationSystem.getAnimationManager().setAnimation(starter, "emote");
    }


    public String getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public Boolean getLoop() {
        return this.entityData.get(IS_LOOP);
    }

    public void setEmote(String emote) {
        this.entityData.set(EMOTE, emote);
    }

    public String getEmote() {
        return this.entityData.get(ANIMATION);
    }


    @Override
    public @NotNull AnimationSystem<NpcEntity> getSystem() {
        return animationSystem;
    }
}
