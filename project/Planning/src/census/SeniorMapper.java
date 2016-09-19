package census;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SeniorMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		Configuration con=context.getConfiguration();
		int eligible=con.getInt("elg",0);
		int year=con.getInt("year",0);
		String data=invalue.toString();
		String dataparts[]=data.split(",");
		int age =Integer.parseInt(dataparts[0]);
		String gender=dataparts[3];
		
		if((age+year)>=eligible)
		{
			if(gender.equals("Male"))
			{
				context.write(new Text("No of Male is goint to added after "+year+" years"),new IntWritable(1));
			}
			else
			{
				context.write(new Text("No of Female is goint to added after "+year+" years"),new IntWritable(2));
				
			}
		}	
		
	}
	
	
}
