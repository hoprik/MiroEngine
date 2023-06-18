package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.hoprik.storymod.Story.Engine.Utils.ChatHeads;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Inject(method = "setLevel(Lnet/minecraft/client/multiplayer/ClientLevel;)V", at = @At("HEAD"))
	public void chatheads$resetServerKnowledge(ClientLevel clientLevel, CallbackInfo ci) {
		// reset every time we enter a world, be it singleplayer or multiplayer
		ChatHeads.serverSentUuid = false;
	}
}
