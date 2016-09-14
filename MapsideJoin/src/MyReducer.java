import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>{
	public void reduce(Text inkey,Iterable<DoubleWritable>invalue,Context context) throws IOException, InterruptedException
	{   double sum=0;
		for(DoubleWritable amt:invalue)
		{
			sum+=amt.get();
		}
		context.write(inkey,new DoubleWritable(sum));	
	}
}
