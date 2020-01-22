package mkii.hadoop.mapreduce.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class FriendFirstMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (value == null){
            return;
        }
        String[] users = StringUtils.split(value.toString(), ':');

        if (users == null || users.length <= 0 || users.length > 2){
            return;
        }
        String[] friends = StringUtils.split(users[1], ',');

        for (String friend : friends) {
            context.write(new Text(friend), new Text(users[0]));
        }
    }
}
