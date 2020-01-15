package mkii.hadoop.rpc.server;

import mkii.hadoop.rpc.common.RpcService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * 发布服务
 *
 */
public class RpcServer {
    public static void main(String[] args) throws IOException {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        // 绑定RpcService的实现在localhost:9999
        builder.setBindAddress("localhost")
                .setPort(9999)
                .setProtocol(RpcService.class)
                .setInstance(new RpcServiceImpl());
        // 打包成服务，并发布
        RPC.Server server = builder.build();
        server.start();
    }
}
