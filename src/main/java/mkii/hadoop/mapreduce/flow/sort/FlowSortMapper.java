package mkii.hadoop.mapreduce.flow.sort;

import mkii.hadoop.mapreduce.flow.summary.FlowInfo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FlowSortMapper extends Mapper<LongWritable, Text, FlowInfo, NullWritable> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowSortMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] fields = StringUtils.split(value.toString(), '\t');
        FlowInfo keyInfo = new FlowInfo();
        FlowInfo valueInfo = new FlowInfo();

        Long download = Long.valueOf(fields[8]);
        Long upload = Long.valueOf(fields[9]);
        Long all = download + upload;

        keyInfo.setPhone(fields[1]);
        keyInfo.setDownload(download);
        keyInfo.setUpload(upload);
        keyInfo.setAll(all);

        LOGGER.info(keyInfo.toString());
        LOGGER.info(valueInfo.toString());

        context.write(keyInfo, NullWritable.get());
    }
}
