package app;

import java.io.IOException;

import app.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;        // primaryStage este prima fereastra creata
	private BorderPane mainLayout;     // frame-ul principal 
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;		    // setter
		this.primaryStage.setTitle("Login");		// definirea titlului pentru prima interfata
		showMainView();				// apelarea functiei showMainView()
	}

	private void showMainView() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();     // declarare variabila de tip FXMLLoader
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));    // declararea path-ului catre fisierul FXML corespunzator primei interfete
		mainLayout = loader.load();			  // incarcarea interfetei
		Scene scene = new Scene(mainLayout);	// setarea scene-ului in care vor fi integrate elementele 
		primaryStage.setScene(scene);			// lansarea frame-ului 
		primaryStage.show();					// vizualizarea frame-ului
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
