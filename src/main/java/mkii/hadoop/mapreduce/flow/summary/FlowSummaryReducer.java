package mkii.hadoop.mapreduce.flow.summary;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;


public class FlowSummaryReducer extends Reducer<Text, FlowInfo, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<FlowInfo> values, Context context) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();
        Long downloadAll = 0L;
        Long uploadAll = 0L;
        Iterator<FlowInfo> iterator = values.iterator();
        while (iterator.hasNext()){
            FlowInfo flowInfo = iterator.next();
            downloadAll += flowInfo.getDownload();
            uploadAll += flowInfo.getUpload();
        }
        builder.append(uploadAll);
        builder.append('-');
        builder.append(downloadAll);
        builder.append('-');
        builder.append(uploadAll + downloadAll);

        context.write(key, new Text(builder.toString()));
    }
}
