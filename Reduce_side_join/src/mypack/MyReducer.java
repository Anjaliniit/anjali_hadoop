package mypack;


import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

public class MyReducer extends Reducer<Text,Text,Text,DoubleWritable> 
{
 @Override
public void reduce(Text inkey,Iterable<Text> invals,Context context) throws IOException, InterruptedException
 {    String name=null;
	 double sum=0;
	 for(Text data:invals)
	 {
	 String dataparts[]=data.toString().split(":");
	 if(dataparts[0].equals("AMT"))
	 {
		 sum+=Double.parseDouble(dataparts[1]);
		 
	 }
	 else if(dataparts[0].equals("Name"))
	 {
		 name=dataparts[1];
	 }
	 else{}
	 }
	 context.write(new Text(name),new DoubleWritable(sum));
}
}