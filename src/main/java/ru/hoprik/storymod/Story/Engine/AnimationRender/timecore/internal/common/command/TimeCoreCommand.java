package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TimeCoreCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        //todo port?
      //  dispatcher.register(Commands.literal("timecore").then(StructureRevealerSubCommand.register()));
    }

    public static void registerClient(CommandDispatcher<CommandSourceStack> dispatcher) {
        //todo port?
       // dispatcher.register(Commands.literal("timecore_client").then(StructureRevealerSubCommand.ClientSubCommand.register()));
    }
}
