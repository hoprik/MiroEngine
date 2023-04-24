package ru.hoprik.storymod.Engine.Hero;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import ru.hoprik.storymod.Engine.StoryFunction;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    public Mob entity;
    static String name = null;

    public Hero(Mob entity, BlockPos pos){
        entity.setPos(pos.getX(),pos.getY(),pos.getZ());
        entity.level.addFreshEntity(entity);
        this.entity = entity;
    }


    public void stopMoveEntity(){
        List<Goal> goals = new ArrayList<>();
        for (WrappedGoal availableGoal : entity.goalSelector.getAvailableGoals()) {
            if (availableGoal.getGoal().toString().equals("MovePlayerEntity")){
                entity.goalSelector.removeGoal(availableGoal.getGoal());
            }
        }

    }

    public void moveEntity(Vector3d vector3d, int speed){
        entity.goalSelector.addGoal(1, new MovePlayerEntity(entity, vector3d, speed));
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
