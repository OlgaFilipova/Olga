package u1u2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import fpt.com.Product;

public class BinaryStrategy implements fpt.com.SerializableStrategy {

	ObjectInputStream is;
	ObjectOutputStream os;
	
	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		Product product = null;
		try {
			product = (Product)is.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub
		os.writeObject(obj);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		is.close();
		os.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		if (input != null) {
			is = new ObjectInputStream(input);
		}
		if (output != null) {
			os = new ObjectOutputStream(output);
		}
	}

}
