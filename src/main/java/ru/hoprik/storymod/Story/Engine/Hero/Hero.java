package ru.hoprik.storymod.Story.Engine.Hero;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec2;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.NpcEntityTorgash;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

import java.io.Serializable;

public class Hero implements Serializable {
    public final Mob entity;
    static String name = null;

    public Hero(Mob entity){
        this.entity = entity;
    }

    public void spawnEntity(BlockPos pos){
        entity.setPos(pos.getX(),pos.getY(),pos.getZ());
        entity.level.addFreshEntity(entity);
    }

    public void stopMoveEntity(){
        for (WrappedGoal availableGoal : entity.goalSelector.getAvailableGoals()) {
            if (availableGoal.getGoal() instanceof MovePlayerEntity){
                entity.goalSelector.removeGoal(availableGoal.getGoal());
            }
        }
    }

    public void setTime(){
        if (entity instanceof NpcEntity) {
            NpcEntity npc = (NpcEntity) entity;
            npc.setTimeCoreAnimation(new AnimationStarter(StoryMod.core.get("animation.npc.walk")));
        }
//        else if (entity instanceof NpcEntityTorgash) {
//            NpcEntityTorgash npc = (NpcEntityTorgash) entity;
//            npc.setAnimation(show, isLoop);
//        }
    }


    public void setAnim(String show, boolean isLoop){
        if (entity instanceof NpcEntity) {
            NpcEntity npc = (NpcEntity) entity;
            npc.setAnimation(show, isLoop);
        }
        else if (entity instanceof NpcEntityTorgash) {
            NpcEntityTorgash npc = (NpcEntityTorgash) entity;
            npc.setAnimation(show, isLoop);
        }
    }

    public void setEmote(String emote){
        NpcEntity npc = (NpcEntity) entity;
        npc.setEmote(emote);
    }

    public void moveEntity(BlockPos blockPos, float speed){
        entity.goalSelector.addGoal(1, new MovePlayerEntity(entity, blockPos, speed));
    }

    public void rotate(Vec2 vec2){
        this.entity.getLookControl().setLookAt(vec2.x, vec2.y, vec2.x);
    }

//    public void pickupOn(){
//        entity.goalSelector.addGoal(1, new PickupGoal(entity));
//    }
//
//    public void pickupOff(){
//        for (WrappedGoal availableGoal : entity.goalSelector.getAvailableGoals()) {
//            if (availableGoal.getGoal() instanceof PickupGoal){
//                entity.goalSelector.removeGoal(availableGoal.getGoal());
//            }
//        }
//    }

    public void giveItem(){
        this.entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.ACACIA_LOG));
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
