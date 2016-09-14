import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer extends Reducer<NullWritable,Text,NullWritable,Text>{
	TreeMap<Integer,Text> details=new TreeMap<Integer,Text>();
}
	public void reduce(NullWritable inkey,Iterable<Text>values,Context context) throws IOException, InterruptedException
	{   double sum=0;
		for(Text t:values)
		{
			details.put(key, value);
		}
		context.write(inkey,new DoubleWritable(sum));	
	}

