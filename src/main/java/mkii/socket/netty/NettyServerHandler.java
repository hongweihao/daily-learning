package mkii.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mkii.rpc.entity.Request;
import mkii.socket.Handler;
import mkii.socket.nio.ConvertUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    // 接收到来自client的数据时调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server reading...");

        // 反射调用对应方法
        Handler handler = new Handler();
        Object o = handler.serverHandler((Request) msg);

        // 编码过程，object需要转换成netty支持的ByteBuf类型
        ByteBuf byteBuf = ConvertUtil.Object2ByteBuf(o);
        // 回写到client
        ctx.write(byteBuf);
    }

    // 读取完数据时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("feedback");
    }

    // 发生异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exception caught");
        cause.printStackTrace();
        ctx.close();
    }

}
