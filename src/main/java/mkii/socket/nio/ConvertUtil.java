package mkii.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.*;
import java.nio.ByteBuffer;

public class ConvertUtil {
    // 将object转换为byte[]
    public static byte[] object2Bytes(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 将byte[]转换为object
    public static Object bytes2Object(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
            }
        }
    }
    // nio
    public static ByteBuffer Object2ByteBuffer(Object object){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object byteBuffer2Object(ByteBuffer byteBuffer){
        byte[] bytes = byteBuffer.array();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
            }
        }
    }

    // netty
    public static Object ByteBuf2Object(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes2Object(bytes);
    }

    public static ByteBuf Object2ByteBuf(Object o){
        byte[] bytes = object2Bytes(o);
        return Unpooled.copiedBuffer(bytes);
    }
}
