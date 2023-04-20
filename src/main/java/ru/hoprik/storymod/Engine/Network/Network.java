package ru.hoprik.storymod.Engine.Network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ru.hoprik.storymod.Engine.Network.Packets.SDialogPacket;
import ru.hoprik.storymod.Engine.Network.Packets.SEndDialogPacket;
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

        net.messageBuilder(SEndDialogPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SEndDialogPacket::new)
                .encoder(SEndDialogPacket::toBytes)
                .consumerMainThread(SEndDialogPacket::handle)
                .add();

        INSTANCE = net;
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
