package census;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class VoterMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{		
		String line=invalue.toString();
		String lineparts[]=line.split(",");
		int age=Integer.parseInt(lineparts[0]);
		int elg=context.getConfiguration().getInt("elg",0);
		int year=context.getConfiguration().getInt("year",0);
		String gender=lineparts[3].trim();
		int tot=age+year;
		if(tot>=elg)
		{
			if(gender.equals("Male"))
				context.write(new Text("Male Voter is going to added after"+year+" years"),new IntWritable(1));
			else
				context.write(new Text("Female Voter is going to added after"+year+" years"),new IntWritable(1));
		}
		
		
	}	
		
	}
	