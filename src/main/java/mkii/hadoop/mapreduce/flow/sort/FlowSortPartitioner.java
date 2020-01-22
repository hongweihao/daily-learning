package mkii.hadoop.mapreduce.flow.sort;

import mkii.hadoop.mapreduce.flow.summary.FlowInfo;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义分组规则：
 * 这里根据手机号码分组
 * 分为{13开头, 15开头, 18开头，其他}，总共4个区
 *
 * 默认的分组组件是HashPartitioner：hash值%分区数目，
 *
 */
public class FlowSortPartitioner extends Partitioner<FlowInfo, NullWritable> {

    // 手机号头两位 -> 区号
    private Map<String, Integer> map;

    /**
     *
     * @param flowInfo mapper output 的 key
     * @param nullWritable mapper output 的 value
     * @param i reduce task 的数目
     * @return 分区号
     */
    @Override
    public int getPartition(FlowInfo flowInfo, NullWritable nullWritable, int i) {
        // 初始化手机号分区
        initPartition();

        String phone = flowInfo.getPhone();
        String prefix = phone.substring(0, 2);

        if (map.containsKey(prefix)){
            return map.get(prefix);
        }else {
            return 3;
        }
    }

    // 模拟手机号分区
    private void initPartition(){
        map = new HashMap<>();
        map.put("13", 0);
        map.put("15", 1);
        map.put("18", 2);
        //map.put("84", 4);
    }
}
