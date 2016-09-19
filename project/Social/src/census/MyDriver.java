package census;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MyDriver {
	
	public static void main(String ar[]) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException
	{   int elg = 0,year=0,choice=0;
		char ch;
	    Configuration conf=new Configuration();
		Scanner sc=new Scanner(System.in);
		FileSystem hdfs=FileSystem.get(conf);
		
		do{
			System.out.println("----------Social Menu----------");
			System.out.println("1 Calculate Pension.");
			System.out.println("2 Calculate orphan.");
			System.out.print("Enter ur choice:");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.print("Enter the eligibility criteria for Pension :");
				try{
				 elg=sc.nextInt();
				}
				catch(Exception e)
				{
				Logger 	log=Logger.getLogger("Eligible");
				log.log(Level.SEVERE,"Eligible Error {0}",e.getMessage());
				}
				
				System.out.print("\nEnter the projection check for next X year :");
				try{
				 year=sc.nextInt();
				}
				catch(Exception e)
				{
				Logger 	log=Logger.getLogger("year error");
				log.log(Level.SEVERE,"Year Error {0}",e.getMessage());
				}
				Path newfolder=new Path(ar[1]);
				conf.setInt("elg",elg);
				conf.setInt("year",year);
				Job job=new Job(conf,"No of Pension");
				job.setJarByClass(MyDriver.class);
				DistributedCache.addCacheFile(new URI("/user/cloudera/pension_table/part-m-00000"),job.getConfiguration());
				job.setNumReduceTasks(1);
				job.setMapperClass(PensionMapper.class);
				job.setReducerClass(MyReducer.class);
				job.setMapOutputKeyClass(Text.class);
				job.setMapOutputValueClass(DoubleWritable.class);
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
				FileOutputFormat.setOutputPath(job,newfolder);
				if(job.waitForCompletion(true))
					hdfs.copyToLocalFile(newfolder,new Path("/home/cloudera/Social"));	
				break;
			case 2:
				Job job1=new Job(conf,"no of Orphan");
			    Path newfolder1=new Path(ar[1]);
			    job1.setJarByClass(MyDriver.class);
			    DistributedCache.addCacheFile(new URI("/user/cloudera/orphan_table/part-m-00000"),job1.getConfiguration());
				job1.setNumReduceTasks(1);
				job1.setMapperClass(OrphanMapper.class);
				job1.setReducerClass(MyReducer.class);
				job1.setMapOutputKeyClass(Text.class);
				job1.setMapOutputValueClass(DoubleWritable.class);
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
			    FileOutputFormat.setOutputPath(job1,newfolder1);
				if(job1.waitForCompletion(true))
					hdfs.copyToLocalFile(newfolder1,new Path("/home/cloudera/Social"));
				break;
				default: System.exit(0);
			}
			System.out.println("Do you want to continue");
			ch=sc.next().charAt(0);
		}  while(ch=='y' || ch=='Y');
	}

}
