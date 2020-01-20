package mkii.hadoop.mapreduce.join.in.reduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoinReducer extends Reducer<Text, ProductBean, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<ProductBean> values, Context context) throws IOException, InterruptedException {

        /*
        注意：
        1. 对象重用
        2. 迭代器只能遍历一次
         */

        List<String> tOrder = new ArrayList<>();
        List<String> tProduct = new ArrayList<>();

        for (ProductBean value : values) {
            String name = value.getName();
            if (name == null || "".equals(name)){
                // 这里相当于对象的副本。
                // 如果把对象放入list中，由于对象重用，会让所有的内容都相同
                String order = value.getId() + "\t" + value.getDate();
                tOrder.add(order);
            }else {
                String product = value.getName() + "\t" + value.getCategoryId() + "\t" + value.getPrice();
                tProduct.add(product);
            }
        }

        for (String s : tOrder) {
            for (String s1 : tProduct) {
                context.write(key, new Text(s + "\t" + s1));
            }
        }
    }
}
