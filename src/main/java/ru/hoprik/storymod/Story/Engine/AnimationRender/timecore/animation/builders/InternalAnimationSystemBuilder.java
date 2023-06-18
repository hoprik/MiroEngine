package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.builders;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationSystem;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.BaseAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.EnumAnimatedObjectType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action.ActionManagerImpl;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action.EntityActionManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action.TileEntityActionManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.builders.PredefinedAnimations.Builder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.builders.PredefinedAnimations.EntityPredefinedAnimations;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.IAnimationManagerBuilder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.IPredefinedAnimations;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.IPredefinedAnimations.IEntityPredefinedAnimations;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class InternalAnimationSystemBuilder {
    public static <T extends Entity & AnimatedObject<T>> AnimationSystem<T> forEntity(
            T entity,
            Level world,
            Consumer<IAnimationManagerBuilder> animationManagerTuner,
            Consumer<IEntityPredefinedAnimations> predefinedAnimationsTuner
    ) {
        return create(EnumAnimatedObjectType.ENTITY, world, animationManagerTuner, (animationManager) -> {
            Builder<EntityPredefinedAnimations> predefinedAnimsBuilder = Builder.of(new EntityPredefinedAnimations());
            predefinedAnimationsTuner.accept(predefinedAnimsBuilder.getInner());
            EntityPredefinedAnimations validatedPredefines = predefinedAnimsBuilder.validate(animationManager);

            return new EntityActionManager<>(animationManager, entity, validatedPredefines);
        });
    }

    public static <T extends BlockEntity & AnimatedObject<T>> AnimationSystem<T> forTileEntity(
            T tileEntity,
            Level world,
            Consumer<IAnimationManagerBuilder> animationManagerTuner,
            Consumer<IPredefinedAnimations> predefinedAnimationsTuner
    ) {
        return create(EnumAnimatedObjectType.TILE_ENTITY, world, animationManagerTuner, (animationManager) -> {
            Builder<PredefinedAnimations> predefinedAnimsBuilder = Builder.of(new PredefinedAnimations());
            predefinedAnimationsTuner.accept(predefinedAnimsBuilder.getInner());
            PredefinedAnimations validatedPredefines = predefinedAnimsBuilder.validate(animationManager);

            return new TileEntityActionManager<>(animationManager, tileEntity, validatedPredefines);
        });
    }

    private static <T extends AnimatedObject<T>> AnimationSystem<T> create(
            EnumAnimatedObjectType type,
            Level world,
            Consumer<? super BaseAnimationManagerBuilder> animationManagerTuner,
            Function<BaseAnimationManager, ? extends ActionManagerImpl<T>> actionManagerBuilderFactory
    ) {
        BaseAnimationManagerBuilder animationManagerBuilder = new BaseAnimationManagerBuilder();
        animationManagerTuner.accept(animationManagerBuilder);

        BaseAnimationManager animationManager = animationManagerBuilder.build(!world.isClientSide(), type);

        ActionManagerImpl<T> actionManager = actionManagerBuilderFactory.apply(animationManager);

        animationManagerBuilder.init(animationManager, actionManager);

        return new AnimationSystem<>(actionManager, animationManager);
    }
}
