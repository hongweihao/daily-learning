package mkii.hadoop.mapreduce.join.in.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable, Text, Text, ProductBean> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String fileName = getFileName(context);
        String[] fields = StringUtils.split(value.toString(), '\t');

        if ("id".equals(fields[0])){
            return;
        }

        Text text;
        ProductBean productBean = new ProductBean();
        if ("t_order".equals(fileName)){
            text = new Text(fields[2]);
            productBean.setId(Long.valueOf(fields[0]));
            productBean.setDate(fields[1]);
        }else{
            text = new Text(fields[0]);
            productBean.setCategoryId(fields[0]);
            productBean.setName(fields[1]);
            productBean.setPrice(fields[3]);
        }
        System.out.println(text + " " + productBean.toString());
        context.write(text, productBean);
    }

    // get file name from context
    private String getFileName(Context context){
        // 获取文件切片信息
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        return fileSplit.getPath().getName();
    }
}
