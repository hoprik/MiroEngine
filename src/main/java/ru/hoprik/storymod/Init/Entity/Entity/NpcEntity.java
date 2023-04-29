package ru.hoprik.storymod.Init.Entity.Entity;


import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class NpcEntity extends AnimalEntity implements IAnimatable {
    //SynchedEntityData
    private static final DataParameter<Boolean> SLEEP =
            EntityDataManager.defineId(NpcEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> MOVE =
            EntityDataManager.defineId(NpcEntity.class, DataSerializers.BOOLEAN);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public NpcEntity(EntityType<? extends AnimalEntity> p_27557_, World p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        super.registerGoals();
    }

    public static AttributeModifierMap setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f).build();
    }

    @Override
    public NpcEntity getBreedOffspring(ServerWorld p_146743_, AgeableEntity p_146744_) {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("story.npc.walking" ));
            return PlayState.CONTINUE;

        }
        if (this.isSleep()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("story.npc.sleep"));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("story.npc.iding"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
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
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        setSleep(tag.getBoolean("isSitting"));
    }



    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSitting", this.isSleep());
        tag.putBoolean("isMoving", this.isSleep());
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SLEEP, false);
        this.entityData.define(MOVE, false);
    }

    public void setSleep(boolean sitting) {
        this.entityData.set(SLEEP, sitting);
    }

    public boolean isSleep() {
        return this.entityData.get(SLEEP);
    }

    public void setMove(boolean sitting) {
        this.entityData.set(MOVE, sitting);
    }

    public boolean isMove() {
        return this.entityData.get(SLEEP);
    }

}
