package mkii.socket.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import mkii.socket.entity.Request;
import mkii.socket.nio.ConvertUtil;

import java.net.InetSocketAddress;

public class NettyClient extends Thread {
    @Override
    public void run() {
        // 客户端引导类
        Bootstrap bootstrap = new Bootstrap();
        // EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(nioEventLoopGroup) //多线程处理
                    .channel(NioSocketChannel.class) //指定通道类型为NioServerSocketChannel，一种异步模式
                    .remoteAddress(new InetSocketAddress("localhost", 10070))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 编码器，将object转换为byte[]
                            socketChannel.pipeline().addLast(new MessageToByteEncoder<Request>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
                                    byte[] bytes = ConvertUtil.object2Bytes(request);
                                    byteBuf.writeBytes(bytes);
                                    channelHandlerContext.flush();
                                }
                            });
                            // 业务处理逻辑
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            try {
                nioEventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NettyClient().start();
    }
}
