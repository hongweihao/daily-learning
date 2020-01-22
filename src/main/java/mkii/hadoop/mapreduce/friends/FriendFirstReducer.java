package mkii.hadoop.mapreduce.friends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendFirstReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // Iterable对象有对象重用的特性，使用list暂时存放以便双重遍历
        List<String> userList = new ArrayList<>();

        for (Text value : values) {
            String user = value.toString();
            userList.add(user);
        }

        for (int i = 0; i < userList.size() - 1; i++) {
            for (int j = i + 1; j < userList.size(); j++){
                String twins = userList.get(i) + "-" + userList.get(j);
                context.write(new Text(twins), key);
            }
        }
    }
}
