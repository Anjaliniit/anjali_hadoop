package pack;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>{
	
	Map<Double,String> list=new TreeMap<>();
	
	public void map(LongWritable inkey,Text invalue, Context context) throws IOException, InterruptedException
	{
		String txn=invalue.toString();
		String txnparts[]=txn.split(",");
		Double amt=Double.parseDouble(txnparts[3]);
		String uid=txnparts[2];
		{
			context.write(new Text(uid),new DoubleWritable(amt));	
		}
		
		
		
		
	}
	
	

}
