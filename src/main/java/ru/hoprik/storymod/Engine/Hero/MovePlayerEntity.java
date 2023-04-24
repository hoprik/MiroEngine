package ru.hoprik.storymod.Engine.Hero;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class MovePlayerEntity extends Goal {

    public static final int WATER_CHECK_DISTANCE_VERTICAL = 1;
    protected final PathfinderMob mob;
    protected double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;
    protected boolean isRunning;

    public MovePlayerEntity(Mob creature,Vector3d vector3d, double speedIn) {
        mob = (PathfinderMob) creature;
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
        this.isRunning = true;
    }

    public void stop() {
        this.isRunning = false;
    }

    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    @Nullable
    protected BlockPos lookForWater(BlockGetter p_198173_, Entity p_198174_, int p_198175_) {
        BlockPos blockpos = p_198174_.blockPosition();
        return !p_198173_.getBlockState(blockpos).getCollisionShape(p_198173_, blockpos).isEmpty() ? null : BlockPos.findClosestMatch(p_198174_.blockPosition(), p_198175_, 1, (p_196649_) -> {
            return p_198173_.getFluidState(p_196649_).is(FluidTags.WATER);
        }).orElse((BlockPos)null);
    }
}
