package ua.dream.chat.network.netty.packet.out;

import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketOutHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;

public class PacketOut4LoginFailed implements Packet<PacketOutHandler> {

    private Reason reason;

    public PacketOut4LoginFailed() {

    }

    public PacketOut4LoginFailed(@NotNull Reason reason) {
        this.reason = reason;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeShort((short) reason.ordinal());
    }

    @Override
    public void read(BinaryReader reader) {
        reason = Reason.values()[reader.readShort()];
    }

    @Override
    public int getPacketID() {
        return 4;
    }

    @Override
    public void handle(PacketOutHandler handler, SocketChannel ctx) {
        handler.handle(this);
    }

    public String getReason() {
        return reason.message;
    }

    public enum Reason {
        WRONG_PASSWORD("wrong password"),
        USER_NOT_REGISTERED("wrong login"),
        SERVER_EXCEPTION("server problems"),
        USER_IS_AUTHORIZED("you is authorized");

        private String message;

        private Reason(String message) {
            this.message = message;
        }
    }
}
