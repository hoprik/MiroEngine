package ru.hoprik.storymod.Story.Engine.Hero;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class MovePlayerEntity extends Goal {

    public static final int WATER_CHECK_DISTANCE_VERTICAL = 1;
    protected final CreatureEntity mob;
    protected double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;

    public MovePlayerEntity(MobEntity creature, Vector3d vector3d, double speedIn) {
        mob = (CreatureEntity) creature;
        speedModifier = speedIn;
        posX = vector3d.x;
        posY = vector3d.y;
        posZ = vector3d.z;
    }

    public boolean canUse() {
        return true;
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
    }

    public void stop() {

    }

    public boolean canContinueToUse() {
        if ( mob.getX() == posX &&  mob.getY() == posY && mob.getY() == posZ) {
            Set<PrioritizedGoal> goal = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, mob.goalSelector, "goals");
            for (PrioritizedGoal availableGoal : goal) {
                if (availableGoal.getGoal().toString().equals("MovePlayerEntity")) {
                    mob.goalSelector.removeGoal(availableGoal.getGoal());
                }
            }
        }
        return !this.mob.getNavigation().isDone();
    }

}
