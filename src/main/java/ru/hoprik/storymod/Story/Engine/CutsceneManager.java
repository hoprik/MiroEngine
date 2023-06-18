package ru.hoprik.storymod.Story.Engine;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.CameraEntity;
import ru.hoprik.storymod.Story.Engine.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.Hero.MovePlayerEntity;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Network.Packets.SCutscene;
import ru.hoprik.storymod.Story.Engine.Utils.ServerSaveData;

public class CutsceneManager {
    CameraEntity entity;
    public CutsceneManager(){
    }

    public void show(Player player){
        ServerSaveData.isCutScene = true;
        entity = new CameraEntity(InitEntity.CAMERA.get(), player.level);
        entity.setPos(new Vec3(0,-50,0));
        player.level.addFreshEntity(entity);
        Network.sendToPlayer(new SCutscene(entity.getId()), player);
    }

    public void moveCamera(BlockPos blockPos, float speed){
        entity.goalSelector.addGoal(1, new MovePlayerEntity(entity, blockPos, speed));
    }

    public void stopCameraMove(){
        entity.setNoGravity(true);
        for (WrappedGoal availableGoal : entity.goalSelector.getAvailableGoals()) {
            if (availableGoal.getGoal().toString().equals("MovePlayerEntity")){
                entity.goalSelector.removeGoal(availableGoal.getGoal());
            }
        }
    }

    public void exitCutscene(Player player){
        Network.sendToPlayer(new SCutscene(player.getId()), player);
        ServerSaveData.isCutScene = false;
    }

}
