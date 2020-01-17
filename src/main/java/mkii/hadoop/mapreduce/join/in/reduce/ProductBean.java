package mkii.hadoop.mapreduce.join.in.reduce;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class ProductBean implements Writable {
    private Long id; // t_order.id
    private String date; // t_order.data
    private String name; // b.name
    private String categoryId; // t_product.category_id
    private String price; // t_product.price

    public ProductBean() {
        init();
    }

    private void init() {
        id = 0L;
        date = "";
        name = "";
        categoryId = "";
        price = ""; // price 定义为Double类型的时候，反序列会报EOFException
    }

    // 调用writeChars序列化多个字符串属性后，反序列化使用readLine会读取全部的值到一个属性中
    // 使用writeUTF和readUTF正常
    // https://stackoverflow.com/questions/18945202/whats-the-difference-between-writeutf-and-writechars
    // writeChars方法使用2byte的char写入，使用readChar读取的时候需要知道长度
    // writeUTF方法使用UTF编码，开头就是长度，所以使用readUTF的时候可以读取到正确长度的内容

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(id);
        dataOutput.writeUTF(date);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(categoryId);
        dataOutput.writeUTF(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readLong();
        date = dataInput.readUTF();
        name = dataInput.readUTF();
        categoryId = dataInput.readUTF();
        price = dataInput.readUTF();
    }
}
