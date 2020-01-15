package mkii.hadoop.rpc.common;

/**
 * 在分布式系统中，service应该是一个独立的模块，并被client和server模块依赖
 *
 */
public interface RpcService {
    // 默认会被设置为 static final
    long versionID = Integer.MAX_VALUE;
    // 默认会被设置为public
    String getMeta(String fileName);
}
