package u1u2;

import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class IdConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(long.class);
	}

	@Override
	public Object fromString(String arg0) {
		// TODO Auto-generated method stub
	        return Long.parseLong(arg0);
	}

	@Override
	public String toString(Object arg0) {
		// TODO Auto-generated method stub
		return ("000000" + arg0.toString()).substring(arg0.toString().length());
	}

}
