package mypack;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper extends Mapper<LongWritable,Text,Text,Text> {

	public void map(LongWritable inputkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		String log=invalue.toString();
		String logparts[]=log.split(" ");
		String logType=logparts[3];
		context.write(new Text(logType), new Text(log));		
	}
}
