package u1u2;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import fpt.com.Product;

public class XMLStrategy implements fpt.com.SerializableStrategy {

	private BeanInfo info;
	private PropertyDescriptor[] propertyDescriptors;
	private XMLDecoder decoder;
	private XMLEncoder encoder;
	
	public XMLStrategy() {
		try {
			BeanInfo info = Introspector.getBeanInfo(Product.class);
			PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		Product readObject = null;
		readObject = (Product)decoder.readObject(); // Read Object
		return readObject;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub
		encoder.writeObject(obj); // write Object
		encoder.flush();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		decoder.close();
		encoder.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		if (input != null) {
		decoder = new XMLDecoder(input);
		} 
		if (output != null) {
		encoder = new XMLEncoder(output);
		}
	}

}
