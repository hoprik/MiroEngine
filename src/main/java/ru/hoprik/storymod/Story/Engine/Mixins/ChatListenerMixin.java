package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.hoprik.storymod.Story.Engine.Utils.ChatHeads;
import ru.hoprik.storymod.StoryMod;

import java.time.Instant;

@Mixin(ChatListener.class)
public abstract class ChatListenerMixin {
    // called from processPlayerChatMessage(), after filtering
    @Inject(
        method = "showMessageToPlayer",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/ChatComponent;addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V"
        )
    )
    public void chatheads$handleAddedPlayerMessage(ChatType.Bound bound, PlayerChatMessage playerChatMessage, Component component, PlayerInfo playerInfo, boolean bl, Instant instant, CallbackInfoReturnable<Boolean> cir) {
        StoryMod.logger.info(component.getString());
        ChatHeads.lastSender = playerInfo;
        ChatHeads.handleAddedMessage(component, playerInfo);
    }

    // called for player messages without UUID
    @Inject(
        method = "processNonPlayerChatMessage",
        at = @At("HEAD")
    )
    public void chatheads$handleAddedSystemSignedPlayerMessage(ChatType.Bound bound, PlayerChatMessage playerChatMessage, Component component, CallbackInfoReturnable<Boolean> cir) {
        StoryMod.logger.info("gg2");
        ChatHeads.handleAddedMessage(component, ChatHeads.lastSender);
    }

    // called for system messages
    @Inject(
            method = "handleSystemMessage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/ChatComponent;addMessage(Lnet/minecraft/network/chat/Component;)V"
            )
    )
    public void chatheads$handleAddedPlayerMessage(Component message, boolean bl, CallbackInfo ci) {
        ChatHeads.handleAddedMessage(message, ChatHeads.lastSender);
    }
}
