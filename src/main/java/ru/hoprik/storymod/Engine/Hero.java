package ru.hoprik.storymod.Engine;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import java.util.Set;

public class Hero {
    public Mob entity;
    static String name = null;

    public Hero(Mob entity, BlockPos pos){
        entity.setPos(pos.getX(),pos.getY(),pos.getZ());
        entity.level.addFreshEntity(entity);
        this.entity = entity;
    }


    public void stopMoveEntity(){
        this.entity.getNavigation().stop();
    }

    public void moveEntity(Vector3d vector3d, int speed){
            entity.goalSelector.addGoal(0, new MovePlayerEntity(entity, vector3d, speed));
    }



    public void rotate(float yaw){
        this.entity.yRotO = yaw;
    }

    public void mobSay(String text, Player player){
        if (name == null){
            name = "Noname";
        }
            StoryFunction.Message(player, name, text);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
