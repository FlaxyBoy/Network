package ua.dream.chat.network.netty.handler;

import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.packet.in.PacketIn1Register;
import ua.dream.chat.network.netty.packet.in.PacketIn2Login;
import ua.dream.chat.network.netty.packet.in.PacketIn7UserMessage;
import ua.dream.chat.network.netty.packet.out.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class NetworkManager {

    private static final Map<Integer , Supplier<Packet<?>>> packetMap = new HashMap<>();

    public static Packet<?> getPacketByID(int id) {
        Supplier<Packet<?>> supplier = packetMap.get(id);
        if(Objects.isNull(supplier)) {
            throw new IllegalArgumentException("packet with id " + id + " not found");
        }
        return supplier.get();
    }

    static {
        add(PacketIn1Register::new);
        add(PacketIn2Login::new);
        add(PacketOut3RegistrationFailed::new);
        add(PacketOut4LoginFailed::new);
        add(PacketOut5UserData::new);
        add(PacketOut6UserLogout::new);
        add(PacketIn7UserMessage::new);
        add(PacketOut8Message::new);
    }

    private static void add(Supplier<Packet<?>> supplier) {
        int id = supplier.get().getPacketID();
        packetMap.put(id , supplier);
    }
}
