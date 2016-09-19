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



public class PlanningDriver {
	
	public static void main(String ar[]) throws IOException, InterruptedException, ClassNotFoundException
	{   int elg = 0,year=0;
		Configuration conf=new Configuration();
		FileSystem hdfs=FileSystem.get(conf);
		Scanner sc=new Scanner(System.in);
		System.out.println("Planning Menu");
		System.out.println("1. No of Senior citizen going to added next X year");
		System.out.println("2. No of Voter's going to added next X year");
		System.out.println("3. No of citizen to immigrants ratio of employment");
		System.out.println("Enter ur choice");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
		System.out.print("Enter the eligibility criteria for Senior Citizen :");
		try{
		 elg=sc.nextInt();
		}
		catch(Exception e)
		{
		Logger 	log=Logger.getLogger("Eligible");
		log.log(Level.SEVERE,"Eligible Error {0}",e.getMessage());
		}
		
		System.out.print("\nEnter the projection check for the next year  :");
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
		Job job=new Job(conf,"Senior Citizen");
		job.setJarByClass(PlanningDriver.class);
		Path newfolder=new Path(ar[1]);
		job.setNumReduceTasks(1);
		job.setMapperClass(SeniorMapper.class);
		job.setReducerClass(PlanningReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setSpeculativeExecution(true);
		try{
			FileInputFormat.addInputPath(job, new Path(ar[0]));
			if(hdfs.exists(newfolder))
			   hdfs.delete(newfolder,true);
		}
		catch(Exception e)
		{
			Logger 	log=Logger.getLogger("Error Message");
			log.log(Level.SEVERE,"Error Message {0}",e.getMessage());
		}
		FileOutputFormat.setOutputPath(job, newfolder);
		if(job.waitForCompletion(true))
			hdfs.copyToLocalFile(newfolder,new Path("/home/cloudera/"));
		break;
		
		case 2:
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
			Job job2=new Job(conf,"Planning Voter");
			job2.setJarByClass(PlanningDriver.class);
			Path newfolder2=new Path(ar[1]);
			job2.setNumReduceTasks(1);
			job2.setMapperClass(VoterMapper.class);
			job2.setReducerClass(PlanningReducer.class);
			job2.setMapOutputKeyClass(Text.class);
			job2.setMapOutputValueClass(IntWritable.class);
			job2.setSpeculativeExecution(true);
			try{
				FileInputFormat.addInputPath(job2, new Path(ar[0]));
				if(hdfs.exists(newfolder2));
				   hdfs.delete(newfolder2,true);
			}
			catch(Exception e)
			{
				Logger 	log=Logger.getLogger("Error Message");
				log.log(Level.SEVERE,"Error Message {0}",e.getMessage());
			}
			FileOutputFormat.setOutputPath(job2, newfolder2);
			if(job2.waitForCompletion(true))
			
				hdfs.copyToLocalFile(newfolder2,new Path("/home/cloudera/"));
			
		break;
		
		case 3:
			Job job1=new Job(conf,"Citizen immigrants");
			job1.setJarByClass(PlanningDriver.class);
			Path newfolder1=new Path(ar[1]);
			job1.setNumReduceTasks(1);
			job1.setMapperClass(CitizenImmigrantsMapper.class);
			job1.setReducerClass(PlanningReducer.class);
			job1.setMapOutputKeyClass(Text.class);
			job1.setMapOutputValueClass(IntWritable.class);
			job1.setSpeculativeExecution(true);
			try{
				FileInputFormat.addInputPath(job1, new Path(ar[0]));
				if(hdfs.exists(newfolder1))
				   hdfs.delete(newfolder1,true);
			}
			catch(Exception e)
			{
				Logger 	log=Logger.getLogger("Error Message");
				log.log(Level.SEVERE,"Error Message {0}",e.getMessage());
			}
			FileOutputFormat.setOutputPath(job1, newfolder1);
			if(job1.waitForCompletion(true))
				hdfs.copyToLocalFile(newfolder1,new Path("/home/cloudera/"));		
		default: System.exit(0);
		break;
		}
	}

}
