package ru.hoprik.storymod.Story.Engine.Entity.Entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.IAnimatable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.PlayState;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.builder.AnimationBuilder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.controller.AnimationController;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.event.predicate.AnimationEvent;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationData;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationFactory;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.util.GeckoLibUtil;

public class NpcEntityTorgash extends Animal implements IAnimatable, Merchant {
    private static final EntityDataAccessor<Boolean> IS_LOOP =
            SynchedEntityData.defineId(NpcEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<String> ANIMATION =
            SynchedEntityData.defineId(NpcEntityTorgash.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> EMOTE =
            SynchedEntityData.defineId(NpcEntityTorgash.class, EntityDataSerializers.STRING);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    protected MerchantOffers offers = new MerchantOffers();
    private final GossipContainer gossips = new GossipContainer();
    private static Player tradeMen;

    public NpcEntityTorgash(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 0));
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
            if (getLoop()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(this.getAnimation()));
            }
            else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(this.getAnimation()));
            }
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new  AnimationBuilder().addAnimation("story.npc.idle"));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState emote(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(this.getEmote()));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controllerMove",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controllerEmote",
                0, this::emote));
    }


    public InteractionResult mobInteract(Player p_35472_, InteractionHand p_35473_) {
        offers.add(new MerchantOffer(new ItemStack(Items.DIRT), new ItemStack(Items.DIAMOND), 2, 2, 2));
        boolean flag = this.getOffers().isEmpty();
        if (flag) {
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            if (!this.level.isClientSide && !this.offers.isEmpty()) {
                this.startTrading(p_35472_);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
    }

    private void startTrading(Player p_35537_) {
        this.updateSpecialPrices(p_35537_);
        this.setTradingPlayer(p_35537_);
        this.openTradingScreen(p_35537_, this.getDisplayName(), 5);
    }


    @Override
    public void setTradingPlayer(@Nullable Player p_45307_) {
        this.tradeMen = p_45307_;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return tradeMen;
    }

    @Override
    public MerchantOffers getOffers() {
        return offers;
    }

    @Override
    public void overrideOffers(MerchantOffers p_45306_) {

    }

    @Override
    public void notifyTrade(MerchantOffer p_45305_) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack p_45308_) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int p_45309_) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return true;
    }

    public int getPlayerReputation(Player p_35533_) {
        return this.gossips.getReputation(p_35533_.getUUID(), (p_186302_) -> {
            return true;
        });
    }


    private void updateSpecialPrices(Player p_35541_) {
        int i = this.getPlayerReputation(p_35541_);
        if (i != 0) {
            for(MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-Mth.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

    }
    public void setOffers(MerchantOffer p_35477_) {
        this.getOffers().add(p_35477_);
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

}
