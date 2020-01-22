package mkii.hadoop.mapreduce.flow.summary;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class FlowInfo implements WritableComparable<FlowInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowInfo.class);

    /*private String timestrap;

    private String mac;
    private String ip;
    private String domain;
    private Long a;
    private Long b;
    private Long c;*/
    private String phone;
    private Long download;
    private Long upload;
    private Long all;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phone);
        dataOutput.writeLong(upload);
        dataOutput.writeLong(download);
        dataOutput.writeLong(all);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        phone = dataInput.readUTF();
        upload = dataInput.readLong();
        download = dataInput.readLong();
        all = dataInput.readLong();
    }

    // 如果mapper的output包含这个类对象，那么会根据这个field排序
    @Override
    public int compareTo(FlowInfo o) {
        // 根据download这个field排序
        return this.download.compareTo(o.getDownload());
    }
}
