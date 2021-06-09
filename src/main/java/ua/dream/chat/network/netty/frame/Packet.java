package ua.dream.chat.network.netty.frame;

import ua.dream.chat.utils.binary.BinaryReader;
import ua.dream.chat.utils.binary.BinaryWriter;
import io.netty.channel.socket.SocketChannel;

public interface Packet <T extends PacketHandler> {

    void write(BinaryWriter writer);

    void read(BinaryReader reader);

    int getPacketID();

    void handle(T handler , SocketChannel ctx);

}
