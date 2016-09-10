package mypack;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<Text, Text> {

	@Override
	public int getPartition(Text key, Text value, int position) {
		String ke=key.toString();
		if(ke.equals("[ERROR]"))
			return 0;
		else if(ke.equals("[DEBUG]"))
			return 1;
		else if(ke.equals("[TRACE]"))
		 return 2;
		else
			return 3;
	}

}
