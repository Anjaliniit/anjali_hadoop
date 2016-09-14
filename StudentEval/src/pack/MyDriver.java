package pack;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyDriver {
	
	public static void main(String ar[]) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration con=new Configuration();
		Job job=new Job(con,"log having maximum no");
		job.setJarByClass(MyDriver.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setNumReduceTasks(1);
		FileInputFormat.addInputPath(job,new Path(ar[0]));
		FileOutputFormat.setOutputPath(job,new Path(ar[1]));
		System.exit(job.waitForCompletion(true)?1:0);
		
		
		
		
	}

}
