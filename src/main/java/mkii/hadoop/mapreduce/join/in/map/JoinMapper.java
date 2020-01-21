package mkii.hadoop.mapreduce.join.in.map;

import mkii.hadoop.mapreduce.join.in.reduce.ProductBean;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JoinMapper extends Mapper<LongWritable, Text, Text, ProductBean> {

    private Map<String, String> map;

    // 在初始化map时调用，先加载小表到内存中
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        map = new HashMap<>();

        Path[] localCacheFiles = context.getLocalCacheFiles();

        for (Path localCacheFile : localCacheFiles) {
            System.out.println(localCacheFile.toString());
        }

        String localFile = localCacheFiles[0].toString();
        URI[] cacheFiles = context.getCacheFiles();


        // 加载小表到内存
        BufferedReader bufferedReader = new BufferedReader(new FileReader("hdfs://localhost:9000/join/reduce/input/t_product"));

        String s;

        while ((s = bufferedReader.readLine()) != null){
            String[] split = s.split("\t");
            map.put(split[0], split[1] + "\t" + split[2] + "\t" + split[3]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {





    }

    // get file name from context
    private String getFileName(Context context){
        // 获取文件切片信息
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        return fileSplit.getPath().getName();
    }
}
