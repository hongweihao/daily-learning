package mkii.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import mkii.socket.nio.ConvertUtil;

import java.util.List;

public class NettyServer extends Thread {
    @Override
    public void run() {
        // server引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 相当于连接池
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try {
            serverBootstrap.group(nioEventLoopGroup)
                    // 注册通道类型
                    .channel(NioServerSocketChannel.class)
                    // 注册监听的ip+port
                    .localAddress("localhost", 10070)
                    // 注册server的处理逻辑
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            // 解码器, 将ByteBuf转换成object
                            channel.pipeline().addLast(new ByteToMessageDecoder() {
                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
                                    Object o = ConvertUtil.ByteBuf2Object(byteBuf);
                                    list.add(o);
                                }
                            });
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            // 开始监听
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("监听：" + channelFuture.channel().localAddress());
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
        new NettyServer().start();
    }
}
