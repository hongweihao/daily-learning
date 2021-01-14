package mkii.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * 自定义的序列化方式
 */
public class ConvertUtil {
    private ConvertUtil() {
    }

    // 将object转换为byte[]
    public static byte[] object2Bytes(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    // 将byte[]转换为object
    public static Object bytes2Object(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // nio
    public static ByteBuffer Object2ByteBuffer(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream ooos = new ObjectOutputStream(baos)) {
            ooos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object byteBuffer2Object(ByteBuffer byteBuffer) {
        byte[] bytes = byteBuffer.array();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // netty
    public static Object byteBuf2Object(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes2Object(bytes);
    }

    public static ByteBuf object2ByteBuf(Object o) {
        byte[] bytes = object2Bytes(o);
        return Unpooled.copiedBuffer(bytes);
    }
}
