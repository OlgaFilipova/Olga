package u1u2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MeinClass2 extends Application {
	
	public static void main(String[] args) {
		
		MeinClass2 mc = new MeinClass2();
		mc.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ViewCustomer vc = new ViewCustomer();
		vc.makeUp();
		
		Scene rootScene = new Scene(vc.getMainPane(), 434, 428);
		primaryStage.setTitle("Shop");

		primaryStage.setScene(rootScene);
		primaryStage.show();
	}

}
