package pack;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class MyMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

	public void map(LongWritable inputkey,Text invalue,Context context) throws IOException, InterruptedException
	{
	
		String log=invalue.toString();
		String logparts[]=log.split(" ");
		String modulename=logparts[2];
		String logtype=logparts[3];
		String type=logtype.trim();
		if(type.equals("ERROR")){
			Text inkey=new Text(modulename);			
			IntWritable inval=new IntWritable(1);
			context.write(inkey, inval);
		}
	}	
}
