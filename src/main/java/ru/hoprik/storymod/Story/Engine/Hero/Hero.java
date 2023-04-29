package ru.hoprik.storymod.Story.Engine.Hero;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.StoryFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Hero {
    public MobEntity entity;
    static String name = null;

    public Hero(MobEntity entity, BlockPos pos){
        entity.setPos(pos.getX(),pos.getY(),pos.getZ());
        entity.level.addFreshEntity(entity);
        this.entity = entity;
    }

    public void stopMoveEntity(){
        Set<PrioritizedGoal> goal = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, entity.goalSelector, "goals");
        for (PrioritizedGoal availableGoal: goal) {
            if (availableGoal.getGoal().toString().equals("MovePlayerEntity")){
                entity.goalSelector.removeGoal(availableGoal.getGoal());
            }
        }
    }

    public void ShowAnim(String show){
        if (Objects.equals(show, "test")){
            NpcEntity npc = (NpcEntity) entity;
            npc.setSleep(true);
        }
    }

    public void moveEntity(Vector3d vector3d, float speed){
        entity.goalSelector.addGoal(1, new MovePlayerEntity(entity, vector3d, speed));
    }

    public void rotate(float yaw){
        this.entity.yRotO = yaw;
    }

    public void mobSay(String text, PlayerEntity player){
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
