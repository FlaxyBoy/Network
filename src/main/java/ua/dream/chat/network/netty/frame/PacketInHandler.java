package ua.dream.chat.network.netty.frame;

import ua.dream.chat.network.netty.packet.in.PacketIn1Register;
import ua.dream.chat.network.netty.packet.in.PacketIn2Login;
import io.netty.channel.socket.SocketChannel;
import ua.dream.chat.network.netty.packet.in.PacketIn7UserMessage;

public interface PacketInHandler extends PacketHandler {

    void handle(PacketIn1Register packet , SocketChannel channel);

    void handle(PacketIn2Login packet , SocketChannel channel);

    void handle(PacketIn7UserMessage packet , SocketChannel channel);
}
