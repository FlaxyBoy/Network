package ua.dream.chat.network.netty.packet.out;

import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;
import ua.dream.chat.network.netty.frame.PacketOutHandler;

public class PacketOut3RegistrationFailed implements Packet<PacketOutHandler> {

    private Reason reason;

    public PacketOut3RegistrationFailed() {

    }

    public PacketOut3RegistrationFailed(@NotNull Reason reason) {
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
        return 3;
    }

    @Override
    public void handle(PacketOutHandler handler, SocketChannel ctx) {
        handler.handle(this);
    }

    public String getReason() {
        return reason.message;
    }

    public enum Reason {
        LOGIN_IS_OCCUPIED("login us occupied"),
        SERVER_EXCEPTION("server problems"),
        TO_MANY_USERS_PER_ACCOUNT("to many users per account"),
        USER_IS_AUTHORIZED("you is authorized");

        private String message;

        private Reason(String message) {
            this.message = message;
        }
    }
}
