package ru.hoprik.storymod.Story.Engine.Bulidings;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.minecraft.core.Direction.NORTH;
import static net.minecraft.world.level.block.BedBlock.OCCUPIED;
import static net.minecraft.world.level.block.BedBlock.PART;
import static net.minecraft.world.level.block.DoorBlock.HINGE;
import static net.minecraft.world.level.block.DoorBlock.OPEN;
import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
import static net.minecraft.world.level.block.PressurePlateBlock.POWERED;
import static net.minecraft.world.level.block.StairBlock.HALF;
import static net.minecraft.world.level.block.state.properties.BedPart.FOOT;
import static net.minecraft.world.level.block.state.properties.BedPart.HEAD;
import static net.minecraft.world.level.block.state.properties.DoorHingeSide.RIGHT;
import static net.minecraft.world.level.block.state.properties.DoubleBlockHalf.LOWER;
import static net.minecraft.world.level.block.state.properties.DoubleBlockHalf.UPPER;

public class BuilderBuildings implements Serializable {

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

        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "jungle_planks")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "sea_lantern")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "warped_door")).defaultBlockState().setValue(HINGE, RIGHT).setValue(DoorBlock.HALF, LOWER).setValue(POWERED, FALSE).setValue(FACING, NORTH).setValue(OPEN, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "air")).defaultBlockState());
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "warped_door")).defaultBlockState().setValue(HINGE, RIGHT).setValue(DoorBlock.HALF, UPPER).setValue(POWERED, FALSE).setValue(FACING, NORTH).setValue(OPEN, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "orange_bed")).defaultBlockState().setValue(PART, HEAD).setValue(FACING, NORTH).setValue(OCCUPIED, FALSE));
        blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "orange_bed")).defaultBlockState().setValue(PART, FOOT).setValue(FACING, NORTH).setValue(OCCUPIED, FALSE));

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

