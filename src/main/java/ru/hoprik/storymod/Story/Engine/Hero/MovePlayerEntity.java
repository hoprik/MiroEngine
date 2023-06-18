package ru.hoprik.storymod.Story.Engine.Hero;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MovePlayerEntity extends Goal {

    public static final int WATER_CHECK_DISTANCE_VERTICAL = 1;
    protected final PathfinderMob mob;
    protected double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;

    public MovePlayerEntity(Mob creature,BlockPos blockPos, double speedIn) {
        mob = (PathfinderMob) creature;
        speedModifier = speedIn;
        posX = blockPos.getX();
        posY = blockPos.getY();
        posZ = blockPos.getZ();
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
        if ( mob.getX() == posX &&  mob.getY() == posY && mob.getY() == posZ){
            List<Goal> goals = new ArrayList<>();
            for (WrappedGoal availableGoal : mob.goalSelector.getAvailableGoals()) {
                if (availableGoal.getGoal().toString().equals("MovePlayerEntity")){
                    mob.goalSelector.removeGoal(availableGoal.getGoal());
                }
            }
        }
        return !this.mob.getNavigation().isDone();
    }
}
