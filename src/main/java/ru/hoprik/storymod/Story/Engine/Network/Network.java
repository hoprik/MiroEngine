package ru.hoprik.storymod.Story.Engine.Network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ru.hoprik.storymod.Story.Engine.Network.Packets.*;
import ru.hoprik.storymod.StoryMod;

import java.io.*;

public class Network{
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(StoryMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        net.messageBuilder(SDialogPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SDialogPacket::new)
                .encoder(SDialogPacket::toBytes)
                .consumerMainThread(SDialogPacket::handle)
                .add();

        net.messageBuilder(SWakeUpPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SWakeUpPacket::new)
                .encoder(SWakeUpPacket::toBytes)
                .consumerMainThread(SWakeUpPacket::handle)
                .add();


        net.messageBuilder(SWaitScreenPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SWaitScreenPacket::new)
                .encoder(SWaitScreenPacket::toBytes)
                .consumerMainThread(SWaitScreenPacket::handle)
                .add();

        net.messageBuilder(SEndDialogPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SEndDialogPacket::new)
                .encoder(SEndDialogPacket::toBytes)
                .consumerMainThread(SEndDialogPacket::handle)
                .add();


        net.messageBuilder(SWaitScreenServerPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SWaitScreenServerPacket::new)
                .encoder(SWaitScreenServerPacket::toBytes)
                .consumerMainThread(SWaitScreenServerPacket::handle)
                .add();

        net.messageBuilder(STeleportPlayer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(STeleportPlayer::new)
                .encoder(STeleportPlayer::toBytes)
                .consumerMainThread(STeleportPlayer::handle)
                .add();

        net.messageBuilder(SPlayerMessagePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SPlayerMessagePacket::new)
                .encoder(SPlayerMessagePacket::toBytes)
                .consumerMainThread(SPlayerMessagePacket::handle)
                .add();

        INSTANCE = net;
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, Player player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), message);
    }

    public static byte[] toByte(Object object){
        try (
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object toObj(byte[] bytes){
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            StoryMod.logger.info(e);
            throw new RuntimeException(e);
        }
    }
}
