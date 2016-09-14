package pack;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class MyMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
	
	TreeMap<Integer,String> map=new TreeMap<>();
	public void setUp(Context context)
	{
		Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());		
		for (Path SinglePath : files) {
			if (SinglePath.getName().equals("custs.dat")) 
			{
				BufferedReader reader = new BufferedReader(new FileReader(SinglePath.toString()));
				String line = reader.readLine();
				while(line != null) 
				{
					String[] lineParts = line.split(",");
					String CustUid = lineParts[0];
					String CustName = lineParts[1]+" "+lineParts[2];
					CustDetails.put(CustUid, CustName);
					line = reader.readLine();
				}
				reader.close();
			}
		}
		if (CustDetails.isEmpty()) 
		{
			throw new IOException("Unable To Load Customer Data.");
		}
		
		
		
	}
	

	public void map(LongWritable inputkey,Text invalue,Context context) throws IOException, InterruptedException
	{
	
		String log=invalue.toString();
		String logparts[]=log.split(" ");
		String modulename=logparts[2];
		String logtype=logparts[3];
		String type=logtype.trim();
		if(type.equals("ERROR")){
			Text inkey=new Text(modulename);			
			IntWritable inval=new IntWritable(1);
			context.write(inkey, inval);
		}
	}	
}
