package mkii.hadoop.mapreduce.flow.summary;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class FlowInfo implements WritableComparable<FlowInfo> {
    private String timestrap;
    private String phone;
    private String mac;
    private String ip;
    private String domain;
    private Long a;
    private Long b;
    private Long c;
    private Long download;
    private Long upload;
    private Long all;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upload);
        dataOutput.writeLong(download);
        dataOutput.writeLong(all);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upload = dataInput.readLong();
        download = dataInput.readLong();
        all = dataInput.readLong();
    }

    @Override
    public int compareTo(FlowInfo o) {
        return all.compareTo(o.all);
    }
}
