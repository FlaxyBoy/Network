package ua.dream.chat.network.netty.packet.in;

import io.netty.channel.socket.SocketChannel;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketInHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import ua.dream.chat.utils.validate.CheckUserData;
import org.jetbrains.annotations.NotNull;

public class PacketIn2Login implements Packet<PacketInHandler> {

    private String userName;
    private String password;

    public PacketIn2Login() {
        userName = null;
        password = null;
    }

    public PacketIn2Login(@NotNull String userName , @NotNull String password) {
        CheckUserData.checkUserName(userName);
        CheckUserData.checkUserPassword(password);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void write(BinaryWriter writer) {
        writer.writeSizedString(userName);
        writer.writeSizedString(password);
    }

    @Override
    public void read(BinaryReader reader) {
        userName = reader.readSizedString(CheckUserData.MAX_USER_NAME_LENGTH);
        password = reader.readSizedString(CheckUserData.MAX_PASSWORD_LENGTH);
    }

    @Override
    public int getPacketID() {
        return 2;
    }

    @Override
    public void handle(PacketInHandler handle , SocketChannel channel) {
        handle.handle(this , channel);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
