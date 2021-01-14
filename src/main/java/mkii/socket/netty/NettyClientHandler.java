package mkii.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mkii.socket.entity.Request;
import mkii.socket.nio.ConvertUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    // 连接server成功时调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active...");
        Request request = new Request();
        request.setClassName("mkii.socket.RemoteClass");
        request.setMethodName("method2");
        request.setParamTypes(new Class[]{String.class});
        request.setParameters(new Object[]{"halo"});
        ctx.write(request);
        ctx.flush();
    }

    // 接收到来自server的消息时调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client reading feedback content...");
        ByteBuf byteBuf = (ByteBuf) msg;
        String s = (String) ConvertUtil.byteBuf2Object(byteBuf);
        System.out.println(s);
    }

    // client异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception caught");
        cause.printStackTrace();
        ctx.close();
    }
}
