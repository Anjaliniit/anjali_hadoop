package census;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>
{  
	
	public void reduce(Text inkeys,Iterable<DoubleWritable> invalues,Context context) throws IOException, InterruptedException
	{
		double sum=0;
		for(DoubleWritable in:invalues)
		{
		  sum+=in.get();
		}
		context.write(inkeys,new DoubleWritable(sum));
		
	}

}
