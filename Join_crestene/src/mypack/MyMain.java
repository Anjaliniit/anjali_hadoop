package mypack;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


	public class MyMain {

		public static void main(String ar[]) throws IOException, ClassNotFoundException, InterruptedException
		{
			Configuration conf=new Configuration();
			Job job=new Job(conf,"transaction2");
			job.setJarByClass(MyMain.class);
			job.setReducerClass(MyReducer.class);
			job.setNumReduceTasks(1);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			MultipleInputs.addInputPath(job,new Path(ar[0]),TextInputFormat.Class,CustomerMapper.class);
			MultipleInputs.addInputPath(job,new Path(ar[1]),TextInputFormat.Class,TransactionMapper.class);
			FileOutputFormat.setOutputPath(job, new Path(ar[2]));
			System.exit(job.waitForCompletion(true)?0:1);
		}	
	}

	
	
	
	
	
	
}
