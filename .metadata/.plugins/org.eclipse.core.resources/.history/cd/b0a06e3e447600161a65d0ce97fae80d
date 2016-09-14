package mypack;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

public class MyReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
{
 public void reduce(Text inkey,Iterable<IntWritable> invals,Context context) throws IOException, InterruptedException
 {
	 int count=0;
	 for(IntWritable iter:invals)
		 count++;
	 context.write(inkey,new IntWritable(count));
 }
}