package u1u2;

import java.util.ArrayList;
import fpt.com.Product;

public class ProductList extends ArrayList<Product> implements fpt.com.ProductList {

	private static final long serialVersionUID = 1L;

	//ruft die Methode "add" von ArrayList
	@Override
	public boolean add(Product e) {
		return super.add(e);
	}

	@Override
	public boolean delete(Product product) {
		return super.remove(product);
	}

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public Product findProductById(long id) {
		for (Product product: this) { //this=ProductList
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (Product product: this) {
			if (product.getName() == name) {
				return product;
			}
		}
		return null;
	}

}
