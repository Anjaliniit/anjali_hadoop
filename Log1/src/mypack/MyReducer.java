package mypack;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


	public class MyReducer extends Reducer<Text,Text,NullWritable,Text> 
	{
	 public void reduce(Text inkey,Iterable<Text> invals,Context context) throws IOException, InterruptedException
	 {
		    for(Text singleValue:invals)
		    context.write(null,singleValue);
     }
}
