package ua.dream.chat.network.netty.packet.out;


import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketOutHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import ua.dream.chat.utils.validate.CheckUserData;

public class PacketOut8Message implements Packet<PacketOutHandler> {

    private String message;
    private String sender;
    private String displayName;
    private Type typeMessage;

    public PacketOut8Message() {}

    public PacketOut8Message(@NotNull String message , @NotNull String sender , @NotNull String displayName , @NotNull Type typeMessage) {
        CheckUserData.checkMessage(message);
        this.message = message;
        this.sender = sender;
        this.displayName = displayName;
        this.typeMessage = typeMessage;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeSizedString(message);
        writer.writeSizedString(sender);
        writer.writeSizedString(displayName);
        writer.writeShort((short) typeMessage.ordinal());
    }

    @Override
    public void read(BinaryReader reader) {
        message = reader.readSizedString(CheckUserData.MAX_MESSAGE_LENGTH);
        sender = reader.readSizedString(CheckUserData.MAX_USER_NAME_LENGTH);
        displayName = reader.readSizedString(CheckUserData.MAX_DISPLAY_NAME_LENGTH);
        typeMessage = Type.values()[reader.readShort()];
    }

    @Override
    public int getPacketID() {
        return 8;
    }

    @Override
    public void handle(PacketOutHandler handler, SocketChannel ctx) {
        handler.handle(this);
    }

    public String getMessage() {
        return message;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSender() {
        return sender;
    }

    public Type getTypeMessage() {
        return typeMessage;
    }

    public static enum Type {
        USER_MESSAGE,
        SERVER_MESSAGE,
        PRIVATE_MESSAGE,
        SEND_PRIVATE_MESSAGE;
    }
}
