package ua.dream.chat.network.netty.packet.in;

import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketInHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import ua.dream.chat.utils.validate.CheckUserData;

public class PacketIn7UserMessage implements Packet<PacketInHandler> {

    private String message;

    public PacketIn7UserMessage() {
        message = null;
    }

    public PacketIn7UserMessage(@NotNull String message) {
        CheckUserData.checkMessage(message);
        this.message = message;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeSizedString(message);
    }

    @Override
    public void read(BinaryReader reader) {
        message = reader.readSizedString(CheckUserData.MAX_MESSAGE_LENGTH);
    }

    @Override
    public int getPacketID() {
        return 7;
    }

    @Override
    public void handle(PacketInHandler handle , SocketChannel channel) {
        handle.handle(this , channel);
    }

    public String getMessage() {
        return message;
    }

}
