package census;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SeniorReducer extends Reducer<Text,IntWritable,Text,IntWritable>{

	public void reduce(Text inkey,Iterable<IntWritable> invalues,Context context) throws IOException,InterruptedException
	{
		int count=0;
		for(IntWritable n:invalues)
		{
			count++;
		}
		context.write(inkey,new IntWritable(count));
		
	}
}
