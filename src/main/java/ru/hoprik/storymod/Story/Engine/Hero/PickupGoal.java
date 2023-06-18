package ru.hoprik.storymod.Story.Engine.Hero;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import ru.hoprik.storymod.StoryMod;

import java.util.ArrayList;
import java.util.List;

public class PickupGoal extends Goal {
    int tick;

    protected final Mob mob;

    public PickupGoal(Mob creature) {
        mob = creature;
    }

    @Override
    public void start() {
        tick = 0;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }

    @Override
    public void tick() {
        if (tick == 20){
            List<Entity> entities = mob.level.getEntities(mob, mob.getBoundingBox().contract(2,2,2));

            entities.forEach(entity -> {
                if (entity instanceof ItemEntity){
                    StoryMod.logger.info(entity);
                    ItemStack itemstack = ((ItemEntity) entity).getItem();
                    mob.onItemPickup((ItemEntity) entity);
                    mob.take(entity, itemstack.getCount());
                    entity.discard();
                }
            });

            tick = 0;
        }
        else {
            tick++;
        }
        super.tick();
    }
}
