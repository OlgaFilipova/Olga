package u1u2;
 
import u1u2.ModelShop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import u1u2.ViewShop;
import u1u2.ControllerShop;

public class MeinClass extends Application {
	
	public static void main(String[] args) {
		
		MeinClass mc = new MeinClass();
		mc.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ModelShop modelShop = new ModelShop();
		ViewShop viewShop = new ViewShop(modelShop);
		viewShop.makeUp();
		
		Scene rootScene = new Scene(viewShop.getMainPane(), 400, 400);
		ControllerShop cShop = new ControllerShop();
		cShop.link(modelShop, viewShop);

		primaryStage.setScene(rootScene);
		primaryStage.show();
		
	}

}
