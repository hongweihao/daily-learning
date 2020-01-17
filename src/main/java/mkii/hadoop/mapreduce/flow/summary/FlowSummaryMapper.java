package mkii.hadoop.mapreduce.flow.summary;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class FlowSummaryMapper extends Mapper<LongWritable, Text, Text, FlowInfo> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] infos = StringUtils.split(value.toString(), '\t');
        if (infos == null || infos.length != 11 || !checkPhone(infos[1])){
            return;
        }
        Long download = Long.valueOf(infos[8]);
        Long upload = Long.valueOf(infos[9]);
        Long all = download + upload;

        FlowInfo flowInfo = new FlowInfo();
        flowInfo.setDownload(download);
        flowInfo.setUpload(upload);
        flowInfo.setAll(all);

        context.write(new Text(infos[1]), flowInfo);
    }

    private boolean checkPhone(String phone){
        return phone != null && phone.length() == 11;
    }
}
