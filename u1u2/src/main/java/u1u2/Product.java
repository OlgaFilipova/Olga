package u1u2;

/*die Klasse, die dafür dient, dass Controller nicht aufgerufen werden muss, um Variable zu verandern 
(View kennt die Variablen, die als Observable zurückgegeben sind*/ 
import javafx.beans.value.ObservableValue;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.*; //wird benutz, damit wir Observable benutzen kann 

public class Product implements fpt.com.Product, java.io.Externalizable {

	//der Parameret, der in Interface Serializable definiert ist, muss hier inizialisiert werden
	private static final long serialVersionUID = 1L; 
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleDoubleProperty price = new SimpleDoubleProperty();
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	private long id; //dient zu Identifizierung des Products
	
	//wenn eine Liste deklarirt wird, müssen die Elemente inizialisirt werden
	public Product() {
		this("", 0, 0, 0);
	}
	
	public Product(String name, long id, double price, int quantity) {
		this.name.set(name);
		this.id = id;
		this.price.set(price);
		this.quantity.set(quantity);
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;	
	}

	@Override
	public double getPrice() {
		return this.price.get();
	}

	//wenn man nur diese Methode benutzt (und keine ObservableValue), muss man noch eine Methode im Controller schreiben
	//mit diese Methode kann man den Wert nicht nur im View ändern
	@Override
	public void setPrice(double price) {
		this.price.set(price);	
	}

	@Override
	public int getQuantity() {
		return this.quantity.get();
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	@Override
	public String getName() {
		return this.name.get();
	}

	@Override
	public void setName(String name) {
		this.name.set(name);
	}

	@Override
	public ObservableValue<String> nameProperty() {
		return this.name;
	}

	@Override
	//Gibt SimpleDoubleProperty zurük, um es mit View zu verbinden
	//Das, was im View gescrieben wird, wird in der Variable gespeichert
	public ObservableValue<Number> priceProperty() {
		return this.price;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		return this.quantity;
	}

	@Override
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.name.set((String)arg0.readObject());
		this.price.set(arg0.readDouble()); 
		this.quantity.set(arg0.readInt());
		this.id = arg0.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeObject(name.get());
		arg0.writeDouble(price.get());
		arg0.writeInt(quantity.get());
		arg0.writeLong(id);;
	}

}
