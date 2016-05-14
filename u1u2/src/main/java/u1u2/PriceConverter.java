package u1u2;

import com.thoughtworks.xstream.converters.SingleValueConverter;

import javafx.beans.property.SimpleDoubleProperty;

public class PriceConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(SimpleDoubleProperty.class);
	}

	@Override
	public Object fromString(String arg0) {
		// TODO Auto-generated method stub
	        return new SimpleDoubleProperty(Double.parseDouble(arg0.replace(".", ",")));
	}

	@Override
	public String toString(Object arg0) {
		// TODO Auto-generated method stub
		return String.format( "%.2f", ((SimpleDoubleProperty)arg0).get()).replace(",", ".");
	}

}
