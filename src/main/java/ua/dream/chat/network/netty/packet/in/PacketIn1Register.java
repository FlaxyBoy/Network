package ua.dream.chat.network.netty.packet.in;

import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.frame.PacketInHandler;
import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import ua.dream.chat.utils.validate.CheckUserData;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class PacketIn1Register implements Packet<PacketInHandler> {

    private String userName;
    private String password;

    public PacketIn1Register() {
        userName = null;
        password = null;
    }


    public PacketIn1Register(@NotNull String userName ,@NotNull String password) {
        CheckUserData.checkUserName(userName);
        CheckUserData.checkUserPassword(password);
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
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
        return 1;
    }

    @Override
    public void handle(PacketInHandler handler , SocketChannel channel) {
        handler.handle(this , channel);
    }
}
