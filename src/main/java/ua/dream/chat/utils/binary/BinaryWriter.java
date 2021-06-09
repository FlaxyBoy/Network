package ua.dream.chat.utils.binary;

import ua.dream.chat.utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;

public class BinaryWriter extends OutputStream {

    private ByteBuf buffer;

    public BinaryWriter(int initialCapacity) {
        this.buffer = Unpooled.buffer(initialCapacity);
    }

    public BinaryWriter(@NotNull ByteBuf buffer) {
        this.buffer = buffer;
    }

    public BinaryWriter(@NotNull ByteBuf... buffers) {
        this.buffer = Unpooled.wrappedBuffer(buffers);
    }

    public BinaryWriter() {
        this.buffer = Unpooled.buffer();
    }

    public void writeBoolean(boolean b) {
        buffer.writeBoolean(b);
    }

    public void writeByte(byte b) {
        buffer.writeByte(b);
    }

    public void writeChar(char c) {
        buffer.writeChar(c);
    }

    public void writeShort(short s) {
        buffer.writeShort(s);
    }

    public void writeInt(int i) {
        buffer.writeInt(i);
    }

    public void writeLong(long l) {
        buffer.writeLong(l);
    }

    public void writeFloat(float f) {
        buffer.writeFloat(f);
    }

    public void writeDouble(double d) {
        buffer.writeDouble(d);
    }

    public void writeVarInt(int i) {
        Utils.writeVarInt(this, i);
    }

    public void writeVarLong(long l) {
        Utils.writeVarLong(this, l);
    }

    public void writeSizedString(@NotNull String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length);
        writeBytes(bytes);
    }

    public void writeVarIntArray(int[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        for (int element : array) {
            writeVarInt(element);
        }
    }

    public void writeBytes(@NotNull byte[] bytes) {
        buffer.writeBytes(bytes);
    }

    public void writeStringArray(@NotNull String[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        for (String element : array) {
            writeSizedString(element);
        }
    }

    public void write(Consumer<BinaryWriter> consumer) {
        if (consumer != null)
            consumer.accept(this);
    }

    public void writeUuid(@NotNull UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    public byte[] toByteArray() {
        byte[] bytes = new byte[buffer.readableBytes()];
        final int readerIndex = buffer.readerIndex();
        buffer.getBytes(readerIndex, bytes);
        return bytes;
    }

    public void writeAtStart(@NotNull BinaryWriter headerWriter) {
        final ByteBuf headerBuf = headerWriter.getBuffer();
        final ByteBuf finalBuffer = Unpooled.wrappedBuffer(headerBuf, buffer);
        setBuffer(finalBuffer);
    }

    public void writeAtEnd(@NotNull BinaryWriter footerWriter) {
        final ByteBuf footerBuf = footerWriter.getBuffer();
        final ByteBuf finalBuffer = Unpooled.wrappedBuffer(buffer, footerBuf);
        setBuffer(finalBuffer);
    }

    public ByteBuf getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void write(int b) {
        writeByte((byte) b);
    }
}