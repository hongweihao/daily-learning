package mkii.hadoop.mapreduce.join.in.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JoinMapper extends Mapper<LongWritable, Text, Text, Text> {

    private Map<String, String> map;

    // 在初始化map时调用，先加载小表到内存中
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        map = new HashMap<>();
        // 取出缓存中的文件
        URI[] cacheFiles = context.getCacheFiles();
        for (URI cacheFile : cacheFiles) {
            // 会打印全文件名
            System.out.println(cacheFile);

            Configcontext.getConfiguration();








        }

        // 从本地读取文件，加载小表到内存，hashMap
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Code\\daily-study\\doc\\hadoop\\mapreduce\\joinCase\\t_product"));






        String s;
        while ((s = bufferedReader.readLine()) != null){
            String[] split = s.split("\t");
            map.put(split[0], split[1] + "\t" + split[2] + "\t" + split[3]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = StringUtils.split(value.toString(), '\t');
        String id = splits[2];

        if ("pid".equals(id)){
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(splits[0]);
        builder.append("\t");
        builder.append(splits[1]);
        builder.append("\t");
        builder.append(map.get(id));

        context.write(new Text(id), new Text(builder.toString()));
    }
}
