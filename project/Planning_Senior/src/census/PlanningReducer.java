package census;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PlanningReducer extends Reducer<Text,IntWritable,Text,IntWritable>
{	
	public void reduce(Text inkeys,Iterable<IntWritable> invalues,Context context) throws IOException, InterruptedException
	{
		int count=0;
		for(IntWritable in:invalues)
		{
			count++;
		}
		context.write(inkeys,new IntWritable(count));
	}	
}
