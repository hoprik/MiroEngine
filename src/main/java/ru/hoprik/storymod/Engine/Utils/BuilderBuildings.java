package ru.hoprik.storymod.Engine.Utils;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BuilderBuildings {
    Level level;
    BlockPos pos;
    List<BlockState> blockStates;
    public BuilderBuildings(Level level, BlockPos pos) {
        this.level = level;
        this.pos = pos;
        this.blockStates = palette();
        build();
    }

    public List<BlockState> palette(){
        List<BlockState> blockStates = new ArrayList<>();

        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "white_wool")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "air")).defaultBlockState());

        return blockStates;
    }

    public void build(){

        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+0), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+1), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+2), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+0), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+1), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+2), blockStates.get(1));

    }
}
