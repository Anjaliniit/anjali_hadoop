package udf1;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class SplitClass extends EvalFunc<String> {

	@Override
	public String exec(Tuple arg0) throws IOException {
		String s=arg0.get(0).toString();
		String s1[]=s.split(" ",5);
		return s1[4];
	}
}
