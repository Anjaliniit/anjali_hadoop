package census;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PensionMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>{
	
	
private Map<Range,Integer> pensionDetails = new HashMap<Range,Integer>();		
	
	protected void setup(Context context) throws java.io.IOException, InterruptedException{
		
		Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());		
		for (Path SinglePath : files) {
			if (SinglePath.getName().equals("part-m-00000")) 
			{
				BufferedReader reader = new BufferedReader(new FileReader(SinglePath.toString()));
				String line = reader.readLine();
				String lineparts[]=line.split(",");
				while(line != null) 
				{   pensionDetails.put(new Range(Double.parseDouble(lineparts[0].trim()),Double.parseDouble(lineparts[1].trim())),Integer.parseInt(lineparts[2].trim()));
					line = reader.readLine();
				}
				reader.close();
			}
		}
		if (pensionDetails.isEmpty()) 
		{
			throw new IOException("Unable To Load Customer Data.");
		}
	}	
	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		String line=invalue.toString();
		String lineparts[]=line.split(",");
		int age=Integer.parseInt(lineparts[0]);
		int elg=context.getConfiguration().getInt("elg",0);
		int year=context.getConfiguration().getInt("year",0);
		double amt=Double.parseDouble(lineparts[5]);
		int tot=age+year;
		if(tot>=elg)
		{  Set<Range> s=pensionDetails.keySet();
            for(Range r:s)
            {
            	double min=r.getMin();
            	double max=r.getMax();
            	if(amt>=min && amt<max)
            	{
                     	int percent=pensionDetails.get(r);
                     	double pension=(percent*amt)/100;
                     	context.write(new Text("Pension"),new DoubleWritable(pension));
            	}
            	
            }
	
				
		}	
	}
}
