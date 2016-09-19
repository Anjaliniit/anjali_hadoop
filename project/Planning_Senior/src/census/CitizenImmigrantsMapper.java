package census;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CitizenImmigrantsMapper  extends Mapper<LongWritable,Text,Text,IntWritable>{
	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		
		String line=invalue.toString();
		String lineparts[]=line.split(",");
		int weeksworked=Integer.parseInt((lineparts[9]).trim());
		String citizen=(lineparts[8]).trim();
		if(weeksworked!=0)
		{
			if(citizen.equals("Foreign born- Not a citizen of U S")){
			context.write(new Text("Not US Citizen :"),new IntWritable(1));			
			}
			else
		    {
			context.write(new Text("US Citizen :"),new IntWritable(1));
		    }
		
		}
	
	}
}