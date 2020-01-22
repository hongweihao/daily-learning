package mkii.hadoop.mapreduce.flow.sort;

import mkii.hadoop.mapreduce.flow.summary.FlowInfo;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowSortReducer extends Reducer<FlowInfo, NullWritable, FlowInfo, NullWritable> {

    @Override
    protected void reduce(FlowInfo key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
