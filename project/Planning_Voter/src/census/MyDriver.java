package census;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MyDriver {
	
	public static void main(String ar[]) throws IOException, InterruptedException, ClassNotFoundException
	{   int elg = 0,year=0;
		Configuration conf=new Configuration();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the eligibility criteria for voting  :");
		try{
		 elg=sc.nextInt();
		}
		catch(Exception e)
		{
		Logger 	log=Logger.getLogger("Eligible");
		log.log(Level.SEVERE,"Eligible Error {0}",e.getMessage());
		}
		System.out.print("Enter the projection for next year's  :");
		try{
		 year=sc.nextInt();
		}
		catch(Exception e)
		{
		Logger 	log=Logger.getLogger("year error");
		log.log(Level.SEVERE,"Year Error {0}",e.getMessage());
		}
		conf.setInt("elg",elg);
		conf.setInt("year",year);
		Job job=new Job(conf,"Planning Voter");
		job.setJarByClass(MyDriver.class);
		FileSystem hdfs=FileSystem.get(conf);
		Path newfolder=new Path(ar[1]);
		job.setNumReduceTasks(1);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setSpeculativeExecution(true);
		FileInputFormat.addInputPath(job, new Path(ar[0]));
		if(hdfs.exists(newfolder))
			hdfs.delete(newfolder,true);
		else
		    FileOutputFormat.setOutputPath(job,newfolder);
		if(job.waitForCompletion(true))
			{
			hdfs.copyToLocalFile(newfolder,new Path("/home/cloudera/"));
			}
		
	}

}
