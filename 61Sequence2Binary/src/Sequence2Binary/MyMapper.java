package Sequence2Binary;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<Text, BytesWritable, NullWritable, NullWritable> {

	public void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {

		FileSystem hdfs = FileSystem.get(new Configuration());

		Path newFilePath = new Path("/abhi123" + key);

		hdfs.createNewFile(newFilePath);

		FSDataOutputStream fsOutStream = hdfs.create(newFilePath);

		fsOutStream.write(value.getBytes());

		fsOutStream.close();
	}
}