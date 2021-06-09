package ua.dream.chat.network.netty.coodec;

import ua.dream.chat.network.netty.frame.Packet;
import ua.dream.chat.utils.Utils;
import ua.dream.chat.utils.binary.BinaryWriter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, ByteBuf byteBuf) throws Exception {
        Utils.writeVarIntBuf(byteBuf , packet.getPacketID());
        packet.write(new BinaryWriter(byteBuf));
    }
}
