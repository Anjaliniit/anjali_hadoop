package census;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class OrphanMapper  extends Mapper<LongWritable,Text,Text,DoubleWritable>{
	
	
private Map<String,Double>orphanDetails = new HashMap<String,Double>();		
	
	protected void setup(Context context) throws java.io.IOException, InterruptedException{
		
		Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());		
		for (Path SinglePath : files) {
			if (SinglePath.getName().equals("part-m-00000")) 
			{
				BufferedReader reader = new BufferedReader(new FileReader(SinglePath.toString()));
				String line = reader.readLine();
				String lineparts[]=line.split(",");
				while(line != null) 
				{   orphanDetails.put(lineparts[0],new Double(lineparts[1]));
					line = reader.readLine();
				}
				reader.close();
			}
		}
		if (orphanDetails.isEmpty()) 
		{
			throw new IOException("Unable To Load Customer Data.");
		}
	}	
	public void map(LongWritable inkey,Text invalue,Context context) throws IOException, InterruptedException
	{
		String line=invalue.toString();
		String lineparts[]=line.split(",");
		int age=Integer.parseInt(lineparts[0]);
		String parents=lineparts[6].trim();
		System.out.println(parents);
		if(age<=18)
		{
		if(parents.equalsIgnoreCase("Not in universe"))
		{ 	System.out.println("hello");
			double amt=orphanDetails.get(parents);
			System.out.println(amt);
		  context.write(new Text("scholarship: "),new DoubleWritable(amt));
	    }
		else if (parents.equalsIgnoreCase("Mother only present") )
		{
			double amt=orphanDetails.get(parents);
			  context.write(new Text("scholarship: "),new DoubleWritable(amt));    	
		}
		else if( parents.equalsIgnoreCase("Father only present"))
		{
			double amt=orphanDetails.get(parents);
			  context.write(new Text("scholarship: "),new DoubleWritable(amt));
		}
		else if( parents.equalsIgnoreCase("Neither parent present"))
		{
		  	double amt=orphanDetails.get(parents);
			  context.write(new Text("scholarship: "),new DoubleWritable(amt));
		}
		else{
		System.out.println("hh");	
		}
			
		}	
			
	}
}