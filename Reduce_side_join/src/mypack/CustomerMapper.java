package mypack;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CustomerMapper extends Mapper<LongWritable,Text,Text,Text>{ 

	@Override
	public void map(LongWritable inputkey,Text invalue,Context context) throws IOException,InterruptedException 
	{
	String txn=invalue.toString();
	String txnparts[]=txn.split(",");
	String uid="UID:"+txnparts[0];
	String name="NAME:"+txnparts[1];
	context.write(new Text(uid),new Text(name));	

	
	}
}