package u1u2;

import u1u2.ModelShop;
import u1u2.ViewShop;
import javafx.event.ActionEvent; //Event in View
import javafx.event.EventHandler; //bearbeitet ein Event

public class ControllerShop implements EventHandler<ActionEvent> {
	
	private ModelShop model;
	private ViewShop view;
	
	public ControllerShop() {
		super();
	}
	
	public void link(ModelShop model, ViewShop view){
		this.model = model;
		this.view = view;
		//die Waren, die in meinem List sind, werden mit ListView verbunden
		view.getWarenShowList().setItems((model.getDelegate()));
	}

	//Macht diese Methode was? Brauchen vielleicht später
	public void handle(ActionEvent event) {
		if (event.getSource().equals(view.getBtnAddProduct())) {
			
		} else if (event.getSource().equals(view.getBtnDelProduct())) {
			
		}
	}
	
}
