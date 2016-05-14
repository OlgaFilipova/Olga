package u1u2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import fpt.com.Product;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ViewShop {
	
	private ModelShop modelShop;
	private GridPane mainPane;
	private ListView<Product> warenShowList;
	private TextField productNameField;
	private TextField productPriceField;
	private TextField productCountField;
	private VBox vBox;
	private HBox hBox;
	private HBox hBox2;
	private Button btnAddProduct;
	private Button btnDelProduct;
	private Button laden;
	private Button speichern;
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Binary-Serialisierung",
		        "Java Beans XML-Serialisierung",
		        "XStream XML-Serialisierung"
		    );
	final ComboBox comboBox = new ComboBox(options);
	private fpt.com.SerializableStrategy str;
	String fileName;
	
	public ViewShop(ModelShop model) {
		initialize(); 
		this.modelShop = model;
	}
	
	private void initialize() {
		mainPane = new GridPane(); //Gitter, wo die grafischen Elementen plazieren werden können
		warenShowList = new ListView<>();
		productNameField  = new TextField();
		productPriceField = new TextField();
		productCountField = new TextField();
		btnAddProduct = new Button("Add");
		btnDelProduct = new Button("Delete");
		btnAddProduct.setPrefWidth(70);
		btnDelProduct.setPrefWidth(70);
		laden = new Button("Laden");
		speichern = new Button("Speichern");
		hBox2 = new HBox(8); 
		hBox = new HBox(8);
		vBox = new VBox();
		vBox.setPrefWidth(200);
		
		//Wie erstehen Zellen in ListView 
		Callback<ListView<Product>, ListCell<Product>> cb = new Callback<ListView<Product>, ListCell<Product>>() {
			
			@Override
			public ListCell<Product> call(ListView<Product> param) { 
				ListCell<Product> cell = new ListCell<Product>() {
					
					@Override
					protected void updateItem(Product item, boolean empty) { //Aktualisiert Zelle aus ListView
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.getName() + "    " + item.getPrice() + " €" + "    " + item.getQuantity() + " Stück" + "    ID: " + item.getId());
						} else {
							setText("");
						}
					}
				};
				return cell;
			}
		};
		warenShowList.setCellFactory(cb); //Cell wird für alle Zelle verwendert
		warenShowList.setOnMouseClicked(new EventHandler<MouseEvent>() { //was passiert, wenn man auf eine Zelle kliegt

			@Override
			public void handle(MouseEvent event) {
				if (warenShowList.getItems().size() == 0) {
					return;
				}
				productNameField.setText(warenShowList.getSelectionModel().getSelectedItem().getName());
				productPriceField.setText(Double.toString(warenShowList.getSelectionModel().getSelectedItem().getPrice()));
				productCountField.setText(Integer.toString(warenShowList.getSelectionModel().getSelectedItem().getQuantity()));
			}
		});
		
		btnDelProduct.setOnAction((e) -> {
			warenShowList.getItems().remove(warenShowList.getSelectionModel().getSelectedItem());
			productNameField.setText("");
			productPriceField.setText("");
			productCountField.setText("");
		});
		
		btnAddProduct.setOnAction((e) -> {
			if (productNameField.getText().trim().isEmpty() //trim - Leerzeichen am Ende und am Anfang - weg, ist dann der name leer?
				|| productPriceField.getText().trim().isEmpty()
				|| productCountField.getText().trim().isEmpty())
			{
				return ;
			} else {
				String productName = productNameField.getText();
				double productPrice = Double.parseDouble(productPriceField.getText());
				int productCount = Integer.parseInt(productCountField.getText());
				try {
					IDGenerator generator = new IDGenerator();
					long productId = generator.idGenerator();
					Product product = new u1u2.Product(productName, productId, productPrice, productCount);
					modelShop.add(product);
				} catch (IDOverflow a) {
					a.printStackTrace();
			}
		}
		}) ;
		
		comboBox.setOnAction((event) -> {
			switch(comboBox.getSelectionModel().getSelectedIndex()) {
				case 0: str = new BinaryStrategy();
				fileName = "products.ser";
				break;
				case 1: str = new XMLStrategy();
				fileName = "products1.xml";
				break;
				case 2: str = new XStreamStrategy();
				fileName = "products2.xml";
				break;
				default: str = null;
				break;
			}
		}
		);
		
		laden.setOnAction((event) -> {
			FileInputStream fi;
			try {
				fi = new FileInputStream(fileName);
				str.open(fi, null);
				try {
					modelShop.clear();
					while (true) {
						modelShop.add(str.readObject());
					}
				} catch (EOFException e){
					e.printStackTrace();
				}
				str.close();
				fi.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});
		
		speichern.setOnAction((event) -> {
			try {
				FileOutputStream fo = new FileOutputStream(fileName);
				str.open(null, fo);
				for (Product product: modelShop) {
					str.writeObject(product);
				}
				str.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
		
	public GridPane getMainPane() {
		return this.mainPane;
	}
	
	public ListView<Product> getWarenShowList() {
		return this.warenShowList;
	}
	
	public TextField getProductNameField() {
		return this.productNameField;
	}
	
	public TextField getProductPriceField() {
		return this.productPriceField;
	}
	
	public TextField getProductCountField() {
		return this.productCountField;
	}
		
	public Button getBtnAddProduct() {
		return this.btnAddProduct;
	}
	
	public Button getBtnDelProduct() {
		return this.btnDelProduct;
	}
	
	public Button getLaden() {
		return this.laden;
	}
	
	public Button getSpeichern() {
		return this.speichern;
	}
	
	public void makeUp() {
		warenShowList.setPrefWidth(200);
		warenShowList.setPrefHeight(400);
	
		Label nameLabel = new Label("Name:");
		Label priceLabel = new Label("Price:");
		Label countLabel = new Label("Count:");
	
		vBox.getChildren().add(nameLabel);
		vBox.getChildren().add(productNameField);
		vBox.getChildren().add(priceLabel);
		vBox.getChildren().add(productPriceField);
		vBox.getChildren().add(countLabel);
		vBox.getChildren().add(productCountField);
		hBox2.getChildren().add(comboBox);
		hBox2.getChildren().add(laden);
		hBox2.getChildren().add(speichern);
		

		vBox.getChildren().add(hBox);
		hBox.getChildren().add(btnAddProduct);
		hBox.getChildren().add(btnDelProduct);
		
		hBox.setPrefWidth(10);
	
		mainPane.add(hBox2, 0, 0, 2, 1);
		mainPane.add(warenShowList, 0, 1);
		mainPane.add(vBox, 1, 1);
		
	}
	
	//Später
	public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
		btnAddProduct.addEventHandler(ActionEvent.ACTION, eventHandler);
	}
}
