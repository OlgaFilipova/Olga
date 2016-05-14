package u1u2;

import javafx.scene.control.*;
import fpt.com.Product;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.*;

public class ViewCustomer {

	private GridPane mainPane;
	private VBox vBox;
	private HBox hBox;
	private Button buy;
	private ListView<Product> products;
	private TableView table;
	
	public ViewCustomer () {
		initialize();
	}
	
	private void initialize() {
		mainPane = new GridPane();
		products = new ListView<Product>();
		table = new TableView();
		TableColumn name = new TableColumn("Name");
        TableColumn price = new TableColumn("Price");
        TableColumn count = new TableColumn("Buy count");
        table.getColumns().addAll(name, price, count);
		buy = new Button("Buy");
		buy.setPrefWidth(70);
		vBox = new VBox(2);
		vBox.setPrefWidth(250);
		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public GridPane getMainPane() {
		return this.mainPane;
	}
	
	public ListView<Product> getProducts() {
		return this.products;
	}
	
	public Button getBuy() {
		return this.buy;
	}
	
	public void makeUp() {
		
		products.setPrefWidth(200);
		products.setPrefHeight(400);
		
		vBox.getChildren().add(table);
		hBox.getChildren().add(buy);
		vBox.getChildren().add(hBox);
		
		mainPane.add(products, 0, 0);
		mainPane.add(vBox, 1, 0);
	}
	
}