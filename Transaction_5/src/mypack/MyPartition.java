package mypack;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition  extends Partitioner<Text, Text> {

	public int getPartition(Text key, Text value, int numPartitions) {

		String myKey = key.toString().toLowerCase();

		if (myKey.equals("01") ) {
			return 0;
		}
		else if (myKey.equals("02")) {
			return 1;
		}
		else if (myKey.equals("03")) {
			return 2;
		}	
		else if (myKey.equals("04")) {
			return 3;
		}				
		
		else if (myKey.equals("05")) {
			return 4;
		}				
		else if (myKey.equals("06")) {
			return 5;
		}						
		
		else if (myKey.equals("07")) {
			return 6;
		}				
		
		else if (myKey.equals("08")) {
			return 7;
		}				
		
		else if (myKey.equals("09")) {
			return 8;
		}				
		
		else if (myKey.equals("10")) {
			return 9;
		}
		
		else if (myKey.equals("11")) {
			return 10;
		}		

		else  {
			return 11;
		}		
		

		
	}
	

}
