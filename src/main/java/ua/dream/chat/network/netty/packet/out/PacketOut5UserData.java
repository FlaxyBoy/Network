package ua.dream.chat.network.netty.packet.out;


import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketOutHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import ua.dream.chat.utils.validate.CheckUserData;

public class PacketOut5UserData implements Packet<PacketOutHandler> {

    private String login;
    private String displayName;

    public PacketOut5UserData() {

    }

    public PacketOut5UserData(@NotNull String login , @NotNull String displayName) {
        CheckUserData.checkUserName(login);
        CheckUserData.checkDisplayName(displayName);
        this.login = login;
        this.displayName = displayName;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeSizedString(login);
        writer.writeSizedString(displayName);
    }

    @Override
    public void read(BinaryReader reader) {
        login = reader.readSizedString(CheckUserData.MAX_USER_NAME_LENGTH);
        displayName = reader.readSizedString(CheckUserData.MAX_DISPLAY_NAME_LENGTH);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public int getPacketID() {
        return 5;
    }

    @Override
    public void handle(PacketOutHandler handler, SocketChannel ctx) {
        handler.handle(this);
    }
}
