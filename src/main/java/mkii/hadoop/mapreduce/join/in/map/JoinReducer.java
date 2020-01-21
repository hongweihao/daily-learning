package mkii.hadoop.mapreduce.join.in.map;

import mkii.hadoop.mapreduce.join.in.reduce.ProductBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class JoinReducer extends Reducer<Text, ProductBean, Text, ProductBean> {
    @Override
    protected void reduce(Text key, Iterable<ProductBean> values, Context context) throws IOException, InterruptedException {

    }
}
