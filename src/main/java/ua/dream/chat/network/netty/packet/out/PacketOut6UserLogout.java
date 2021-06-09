package ua.dream.chat.network.netty.packet.out;


import io.netty.channel.socket.SocketChannel;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketOutHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;

public class PacketOut6UserLogout implements Packet<PacketOutHandler> {

    private String reason;

    public PacketOut6UserLogout() {
        reason = "unknown";
    }

    public PacketOut6UserLogout(String reason) {
        this.reason = reason;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeSizedString(reason);
    }

    @Override
    public void read(BinaryReader reader) {
        reason = reader.readSizedString(128);
    }

    public String getReason() {
        return reason;
    }

    @Override
    public int getPacketID() {
        return 6;
    }

    @Override
    public void handle(PacketOutHandler handler, SocketChannel ctx) {
        handler.handle(this);
    }

}
