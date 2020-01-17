package mkii.hadoop.mapreduce.join.in.reduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<Text, ProductBean, Text, ProductBean> {
    @Override
    protected void reduce(Text key, Iterable<ProductBean> values, Context context) throws IOException, InterruptedException {

        ProductBean product = null;

        Iterator<ProductBean> iterator = values.iterator();

        for (ProductBean productBean : values) {
            String name = productBean.getName();
            System.out.println("reduce： " + productBean.toString());
            if (name != null && !"".equals(name)){
                product = productBean;
                break;
            }
        }

        if (product == null){
            return;
        }

        while (iterator.hasNext()){
            ProductBean  productBean = iterator.next();
            String name = productBean.getName();
            if (name == null || "".equals(name)){
                System.out.println("");

                productBean.setName("name");
                productBean.setCategoryId(product.getCategoryId());
                productBean.setPrice(product.getPrice());
                context.write(new Text(productBean.toString()), productBean);
            }
        }
    }
}
