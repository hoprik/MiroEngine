package ru.hoprik.storymod.Init.Entity.Entity;

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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.Executer;
import ru.hoprik.storymod.StoryMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;

public class NpcEntity extends Animal implements IAnimatable {
    private static final EntityDataAccessor<String> ANIMATION =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> EMOTE =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.STRING);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public NpcEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
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
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation(this.getAnimation()));
            return PlayState.CONTINUE;
        }
//        if (this.isSleeping()) {
//            event.getController().setAnimation(new AnimationBuilder()
//                    .addAnimation("story.npc.sleep"));
//            return PlayState.CONTINUE;
//        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("story.npc.iding"));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState emote(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder()
                .addAnimation(this.getEmote()));
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
        setAnimation(tag.getString("animation"));
        setAnimation(tag.getString("emote"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("animation", this.getAnimation());
        tag.putString("emote", this.getEmote());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, "");
        this.entityData.define(EMOTE, "");
    }

    public void setAnimation(String animations) {
        this.entityData.set(ANIMATION, animations);
    }

    public String getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setEmote(String emote) {
        this.entityData.set(EMOTE, emote);
    }

    public String getEmote() {
        return this.entityData.get(ANIMATION);
    }


}
