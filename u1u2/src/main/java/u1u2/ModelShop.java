package u1u2;
//ObservableList - Elemente nur hinzufügen und löschen, ModifiableObservableList - Elemente auch verändern (abstrakte Klasse)
import javafx.collections.ModifiableObservableListBase; 
import u1u2.ProductList;
import fpt.com.Product;
import javafx.collections.FXCollections; //um in ListView ProductList anzuzeigen
import javafx.collections.ObservableList;

public class ModelShop extends ModifiableObservableListBase<Product> {

	private final ObservableList<fpt.com.Product> delegate; //die Liste, die aus Elemente besteht, die Product implementieren
	
	public ModelShop() {
		
		delegate = FXCollections.observableArrayList(new ProductList()); //macht aus ProductList ein ObservableArrayList	
	}
		
	public ObservableList<Product> getDelegate() {
		
		return this.delegate;
	}
	
	@Override
	public Product get(int index) {
		
		return delegate.get(index);
	}
	
	@Override
	public int size() {
		
		return delegate.size();
	}
	
	@Override
	protected void doAdd(int index, Product element) {
		for (Product e: this) {
			if (e.getId() == element.getId()) {
				return;
			}
		}
		delegate.add(index, element);
	}

	@Override
	protected Product doRemove(int index) {
		Product productRemoved = delegate.remove(index);
		return productRemoved;
	}

	@Override
	protected Product doSet(int index, Product element) {
		
		Product productSeted = delegate.set(index, element);
		return productSeted;
	}

}
