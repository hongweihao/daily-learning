package mkii.hadoop.rpc.client;

import mkii.hadoop.rpc.common.RpcService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 请求远程的服务，远程模块会返回一个service对象，所以需要依赖service模块
 *
 */
public class RpcClient {
    public static void main(String[] args) throws IOException {
        // 远程获取对象
        RpcService rpcService = RPC.getProxy(RpcService.class, 1L,new InetSocketAddress("localhost", 9999), new Configuration());
        String meta = rpcService.getMeta("userBlock");
        System.out.println(meta);
    }
}
