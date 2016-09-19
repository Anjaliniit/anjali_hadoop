package census;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MyDriver {
	public static void main(String ar[] ) throws IOException, InterruptedException, ClassNotFoundException
	{
		Scanner sc=new Scanner(System.in);insert overwrite table census
		select get_json_object(censusdata.jsondata,'$.Age'),
		
		
		System.out.println("Enter the Eligibility Age:");
		try{
		int elg=sc.nextInt();
		}
		catch(Exception e)
		{Logger
			
		}
		System.out.println("Enter the year");
		int ear=sc.nextInt();
		Configuration conf=new Configuration();
		conf.setInt("year",year);
		conf.setInt("eligible",elg);
		Job job=new Job(conf,"Senior citizen demo");
		job.setJarByClass(MyDriver.class);
		job.setMapperClass(SeniorMapper.class);
		job.setReducerClass(SeniorReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
	    job.setNumReduceTasks(1);
	    job.setSpeculativeExecution(true);
	    FileInputFormat.addInputPath(job,new Path(ar[0]));
	    Path output=new Path(ar[0]);
	    if(output.)
	    FileOutputFormat.setOutputPath(job,output);
	    System.out.println(job.waitForCompletion(true)?1:0);		
		
	}

}
