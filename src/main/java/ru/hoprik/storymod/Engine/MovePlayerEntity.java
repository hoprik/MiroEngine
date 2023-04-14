package ru.hoprik.storymod.Engine;

import com.mojang.math.Vector3d;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.EnumSet;

public class MovePlayerEntity extends Goal {

    private final Mob creature;
    private final double speed;
    private Vector3d vector3d;
    private PathNavigation navigation;

    public MovePlayerEntity(Mob creature,Vector3d vector3d, double speedIn) {
        this.creature = creature;
        this.speed = speedIn;
        this.vector3d = vector3d;
        this.navigation = creature.getNavigation();
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void start() {
        this.navigation.moveTo(this.vector3d.x, this.vector3d.y, this.vector3d.z, 5);
    }
    @Override
    public void stop() {
        this.navigation.stop();
    }
    @Override
    public void tick() {
        this.navigation.moveTo(this.vector3d.x, this.vector3d.y, this.vector3d.z, 5);
    }
    @Override
    public boolean canUse() {
        return false;
    }
}
