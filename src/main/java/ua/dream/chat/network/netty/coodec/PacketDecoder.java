package ua.dream.chat.network.netty.coodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.network.netty.handler.NetworkManager;
import ua.dream.chat.utils.binary.BinaryReader;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        BinaryReader reader = new BinaryReader(byteBuf);
        Packet<?> packet = NetworkManager.getPacketByID(reader.readVarInt());
        packet.read(reader);
        list.add(packet);
    }
}
