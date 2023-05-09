package app.view;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username;		// textField pentru numele de utilizator

    @FXML
    private PasswordField password;		// textField pentru parola (sunt afisate buline)
    
    @FXML
    private CheckBox checkUser;		// CheckBox pentru logare
    
    public static String userName;		// copie a continutului din username
    public static String passWord;		// copie a continutului din password

    @FXML
    void onLoginClick(ActionEvent event) {
    	if(checkUser.isSelected())                 // daca checkbox-ul este selectat
    	{
            try {
            	userName = username.getText();		// preluare continut din textField-ul username
            	passWord = password.getText();		// preluare continut din textField-ul password
                FXMLLoader fxmlLoader = new FXMLLoader();		// declarare variabila de tip FXMLLoader
                fxmlLoader.setLocation(getClass().getResource("ImageWindow.fxml"));    // declararea path-ului catre fisierul FXML corespunzator celei de-a doua interfete
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);   // setarea dimensiunilor frame-ului
                Stage stage = new Stage();			// declarare variabila de tip Stage
                stage.setTitle("Convert Image to Gray-Scale Image");   // setarea titlului pentru stage-ul curent
                stage.setScene(scene);		// adaugarea frame-ului la stage
                stage.show();				// vizualizarea stage-ului
                ((Node)(event.getSource())).getScene().getWindow().hide();  // inchiderea primei interfete
            } catch (IOException e) {
                e.printStackTrace();			// afisarea exceptiilor
            }
    	}
    }

}
