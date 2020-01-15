package mkii.hadoop.rpc.server;

import mkii.hadoop.rpc.common.RpcService;

/**
 * 实现service接口，具体的实现
 *
 */
public class RpcServiceImpl implements RpcService {
    @Override
    public String getMeta(String fileName) {
        return fileName + "'s block info: {BLK01:/DATA01.block, BLK02:/DATA02.block}..." ;
    }
}
