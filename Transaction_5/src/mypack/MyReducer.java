package mypack;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MyReducer extends Reducer<Text,Text,Text,Text> {
	
	public void reduce(Text inkey,Iterable<Text> invalue,Context context) throws IOException, InterruptedException
	{
		for(Text itr:invalue)
		context.write(inkey,itr);
		
	}
}
