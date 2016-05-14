package u1u2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fpt.com.ExternalizableReflectionConverter;
import fpt.com.Product;

public class XStreamStrategy implements fpt.com.SerializableStrategy {

	XStream xstream;
	private ObjectInputStream decoder;
	private ObjectOutputStream encoder;
	
	public XStreamStrategy() {
		xstream = new XStream(new DomDriver());
		xstream.registerConverter(new ExternalizableReflectionConverter(xstream), XStream.PRIORITY_LOW);
		xstream.registerConverter(new IdConverter());
		xstream.registerConverter(new PriceConverter());
		xstream.useAttributeFor(u1u2.Product.class, "id");
	}
	
	@Override
	public Product readObject() throws IOException, EOFException {
		// TODO Auto-generated method stub
		try {
			return (Product)decoder.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub
		encoder.writeObject(obj);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(decoder != null){
			decoder.close();
		}
		if(encoder != null) {
			encoder.close();
		}
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		if (input != null) {
			decoder = xstream.createObjectInputStream(input);
			} 
			if (output != null) {
			encoder = xstream.createObjectOutputStream(output);
			}
	}

}
