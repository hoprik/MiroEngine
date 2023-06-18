package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "sendChat", at = @At(value = "HEAD"), cancellable = true)
    public void onSendChatMessage(String p_241300_, Component p_241533_, CallbackInfo ci) {
        if (p_241300_.startsWith("/")) {
//            if (ClientCommandManager.handleCommand(Minecraft.getInstance().player.createCommandSourceStack(), message)) {
//                ci.cancel();
//            }
        }
    }
}
