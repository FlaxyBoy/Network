package ua.dream.chat.utils.binary;

import ua.dream.chat.utils.Utils;
import ua.dream.chat.utils.validate.Check;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class BinaryReader extends InputStream {

    private final ByteBuf buffer;

    public BinaryReader(@NotNull ByteBuf buffer) {
        this.buffer = buffer;
    }

    public BinaryReader(byte[] bytes) {
        this(Unpooled.wrappedBuffer(bytes));
    }

    public int readVarInt() {
        return Utils.readVarInt(buffer);
    }

    public boolean readBoolean() {
        return buffer.readBoolean();
    }

    public byte readByte() {
        return buffer.readByte();
    }

    public short readShort() {
        return buffer.readShort();
    }

    public char readChar() {
        return buffer.readChar();
    }

    public int readUnsignedShort() {
        return buffer.readUnsignedShort();
    }

    public int readInteger() {
        return buffer.readInt();
    }

    public long readLong() {
        return buffer.readLong();
    }

    public float readFloat() {
        return buffer.readFloat();
    }

    public double readDouble() {
        return buffer.readDouble();
    }

    public String readSizedString(int maxLength) {
        final int length = readVarInt();
        Check.stateCondition(length > maxLength,
                "String length (" + length + ") was higher than the max length of " + maxLength);

        final byte[] bytes = readBytes(length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public byte[] readBytes(int length) {
        ByteBuf buf = buffer.readBytes(length);
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        buf.release();
        return bytes;
    }

    public String[] readSizedStringArray(int maxLength) {
        final int size = readVarInt();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = readSizedString(maxLength);
        }
        return strings;
    }

    public int[] readVarIntArray() {
        final int size = readVarInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = readVarInt();
        }
        return array;
    }

    public byte[] getRemainingBytes() {
        return readBytes(buffer.readableBytes());
    }


    public UUID readUuid() {
        final long most = readLong();
        final long least = readLong();
        return new UUID(most, least);
    }


    public ByteBuf getBuffer() {
        return buffer;
    }

    @Override
    public int read() {
        return readByte() & 0xFF;
    }

    @Override
    public int available() {
        return buffer.readableBytes();
    }

}