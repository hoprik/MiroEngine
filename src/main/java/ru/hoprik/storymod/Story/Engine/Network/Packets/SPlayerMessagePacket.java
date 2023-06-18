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

import java.util.function.Supplier;

public class SPlayerMessagePacket {
    private final String hero;
    private final String text;
    public SPlayerMessagePacket(String hero, String text){
        this.hero = hero;
        this.text = text;
    }
    public SPlayerMessagePacket(FriendlyByteBuf buf){
        this.hero = buf.readUtf();
        this.text = buf.readUtf();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(hero);
        buf.writeUtf(text);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer playerAll = context.getSender();
            for (Player player: playerAll.getServer().getPlayerList().getPlayers()) {
                //player.sendSystemMessage(Component.literal(ChatFormatting.DARK_PURPLE +"["+hero+"]"+ChatFormatting.RESET+" "+text));
                MutableComponent message = Component.empty();
                MutableComponent hero_comp = Component.literal("["+hero+"] ").withStyle(style -> style.withColor(Settings.colorChat).withFont(Settings.fontHero));
                MutableComponent text_comp = Component.literal(text).withStyle(style -> style.withColor(ChatFormatting.WHITE).withFont(Settings.fontText));

                message.append(hero_comp);
                message.append(text_comp);

                player.sendSystemMessage(message);
            }
        });
        return true;
    }
}
