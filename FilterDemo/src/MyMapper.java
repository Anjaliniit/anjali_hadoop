import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable,Text,NullWritable,Text> {
	
	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		Configuration conf=context.getConfiguration();
		String userid=conf.get("uid");
		String userdata=invalue.toString();
		String userparts[]=userdata.split(",");
		String id=userparts[0];
		//String prof=userparts[4];
		if(userid.equals(id))
		{
			context.write(null,new Text(userdata));
		}
		
		
		
		
	}

}
