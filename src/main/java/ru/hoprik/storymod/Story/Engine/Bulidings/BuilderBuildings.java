package ru.hoprik.storymod.Story.Engine.Bulidings;


import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static net.minecraft.block.BedBlock.OCCUPIED;
import static net.minecraft.block.BedBlock.PART;
import static net.minecraft.block.DoorBlock.HINGE;
import static net.minecraft.block.DoorBlock.OPEN;
import static net.minecraft.block.DoorBlock.POWERED;
import static net.minecraft.state.properties.DoorHingeSide.RIGHT;
import static net.minecraft.state.properties.DoubleBlockHalf.LOWER;
import static net.minecraft.state.properties.DoubleBlockHalf.UPPER;
import static net.minecraft.util.Direction.NORTH;


public class BuilderBuildings implements Serializable {

    World level;
    BlockPos pos;
    List<BlockState> blockStates;
    public BuilderBuildings(World level, BlockPos pos) {
        this.level = level;
        this.pos = pos;
        this.blockStates = palette();
        build();
    }

    public List<BlockState> palette(){
        List<BlockState> blockStates = new ArrayList<>();

        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "jungle_planks")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "sea_lantern")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "warped_door")).defaultBlockState().setValue(HINGE, RIGHT).setValue(DoorBlock.HALF, LOWER).setValue(POWERED, FALSE).setValue(DoorBlock.FACING, NORTH).setValue(OPEN, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "air")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "warped_door")).defaultBlockState().setValue(HINGE, RIGHT).setValue(DoorBlock.HALF, UPPER).setValue(POWERED, FALSE).setValue(DoorBlock.FACING, NORTH).setValue(OPEN, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "orange_bed")).defaultBlockState().setValue(PART, BedPart.HEAD).setValue(BedBlock.FACING, NORTH).setValue(OCCUPIED, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "orange_bed")).defaultBlockState().setValue(PART, BedPart.FOOT).setValue(BedBlock.FACING, NORTH).setValue(OCCUPIED, FALSE));

        return blockStates;
    }

    public void build(){

        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+0, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+0, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+0, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+0, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+1, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+1, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+1, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+1, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+1, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+2, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+2, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+2, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+2, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+2, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+2, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+2, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+2, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+2, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+2, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+2, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+3, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+3, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+3, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+3, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+3, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+3, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+3, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+3, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+3, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+3, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+3, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+3, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+4, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+4, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+4, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+0, pos.getY()+4, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+4, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+4, pos.getZ()+1), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+4, pos.getZ()+2), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+4, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+4, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+4, pos.getZ()+1), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+4, pos.getZ()+2), blockStates.get(1));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+4, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+4, pos.getZ()+0), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+4, pos.getZ()+1), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+4, pos.getZ()+2), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+3, pos.getY()+4, pos.getZ()+3), blockStates.get(0));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+1, pos.getZ()+0), blockStates.get(2));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+1, pos.getZ()+1), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+1, pos.getZ()+2), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+2, pos.getZ()+1), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+2, pos.getZ()+2), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+2, pos.getZ()+0), blockStates.get(4));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+2, pos.getZ()+1), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+2, pos.getZ()+2), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+3, pos.getZ()+1), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+3, pos.getZ()+2), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+3, pos.getZ()+1), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+2, pos.getY()+3, pos.getZ()+2), blockStates.get(3));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+1), blockStates.get(5));
        level.setBlockAndUpdate(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+2), blockStates.get(6));

    }
}

