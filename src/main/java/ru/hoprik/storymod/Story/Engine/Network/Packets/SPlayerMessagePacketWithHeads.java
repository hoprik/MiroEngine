package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Settings;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.Story.Engine.Utils.ChatHeads;
import ru.hoprik.storymod.StoryMod;

import java.util.function.Supplier;

public class SPlayerMessagePacketWithHeads {
    private final String hero;
    private final String text;
    private final ResourceLocation location;
    public SPlayerMessagePacketWithHeads(String hero, String text, ResourceLocation location){
        this.hero = hero;
        this.text = text;
        this.location= location;
    }
    public SPlayerMessagePacketWithHeads(FriendlyByteBuf buf){
        this.hero = buf.readUtf();
        this.text = buf.readUtf();
        this.location = buf.readResourceLocation();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(hero);
        buf.writeUtf(text);
        buf.writeResourceLocation(location);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer playerAll = context.getSender();
                for (Player player : playerAll.getServer().getPlayerList().getPlayers()) {

                    StoryMod.logger.info(location.toString());

                    ChatHeads.lastPlayerSkin = location;

                    MutableComponent message = Component.empty();
                    MutableComponent hero_comp = Component.literal("["+hero+"] ").withStyle(style ->  style.withColor(Settings.colorChat));
                    MutableComponent text_comp = Component.literal(text).withStyle(style -> style.withColor(ChatFormatting.WHITE));
                    message.append(hero_comp);
                    message.append(text_comp);
                    player.sendSystemMessage(message);
            }
        });
        return true;
    }
}
