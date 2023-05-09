package app.view;

import java.io.File;


import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	
	public class ImageResult extends Application {
	  
		public static void main(String[] args) {
		    launch(args);
		  }
		
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        
	        StackPane root = new StackPane();    // container pentru elementele de control
	       
	        Scene scene = new Scene(root, 425, 650);   // dimensiunea containerului
	 
	        primaryStage.setTitle("Image Result");   // primaryStage este prima fereastra creata
	        primaryStage.setScene(scene);		// adaugarea containerului in fereastra
	        primaryStage.show();				// afisarea frame-ului
	        
	        
	        TableView tableView = new TableView();   // declarea unui tableView
	        tableView.setEditable(true);             // valorile din tabel se pot modifica
	        
	        TableColumn<String, MyFileCollection> column1 = new TableColumn<>("File Name");  // declararea coloanei pentru numele fisierului
	        column1.setCellValueFactory(new PropertyValueFactory<>("fileName"));   //  declararea valorii fileName din clasa MyFileCollection cu care va popula prima coloana


	        TableColumn<String, MyFileCollection> column2 = new TableColumn<>("File Size (KB)");  // declararea coloanei pentru dimenstiunea fisierului
	        column2.setCellValueFactory(new PropertyValueFactory<>("fileSize"));  // declararea valorii fileSize din clasa MyFileCollection cu care va popula cea de-a doua coloana


	        tableView.getColumns().add(column1);  // adaugarea primei coloane in tabel
	        tableView.getColumns().add(column2);  // adaugarea celei de-a doua coloane in tabel
	        
	        
	        File folder = new File("src/img/");		//declararea folderului "img"
	        File[] listOfFiles = folder.listFiles();	// vector cu continutul folderului "src/img/"
	        
	        for (int i = 0; i < listOfFiles.length; i++) {	// parcurgere vector de fisiere
	        	  if (listOfFiles[i].isFile()) 		// daca elementul la care s-a ajuns este fisier
	        	  {
	        		  tableView.getItems().add(new MyFileCollection(listOfFiles[i].getName(), listOfFiles[i].length()/1024));  // popularea unui rand din tabela cu numele fisierului alaturi de dimensiunea sa in KB
	        	  } 	  
	        }

	        tableView.setLayoutX(100);   // setarea pozitiei pe axa OX de unde va fi afisat tabelul
	        tableView.setLayoutY(200);   // setarea pozitiei pe axa OY de unde va fi afisat tabelul
	        tableView.setMaxWidth(400);  // latimea maxima a tabelului
	        tableView.setPrefHeight(200);  // inaltimea predefinita a tabelului
	        
	        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // pastrarea dimensiunilor tabelului
	        
	        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5)); // prima coloana se va intinde pe jumatate din latimea tabelului
	        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5)); // a doua coloana se va intinde pe cealalta jumatate din latinea tabelului

	        column1.setResizable(false);   // coloana 1 nu isi va schimba dimensiunea daca utilizatorul incearca sa traga de ea din interfata
	        column2.setResizable(false);   // coloana 2 nu isi va schimba dimensiunea daca utilizatorul incearca sa traga de ea din interfata
	        
	        TextField name = new TextField();   // declarare textField pentru numele de utilizator
	        name.setPromptText("Get your username");  // setarea unui placeholder in lipsa afisarii de valori in textField
	        name.setVisible(true);   // setare vizibilitate textField
	        name.setLayoutX(50);   // setarea pozitiei pe axa OX de unde va fi afisat textField-ul
	        name.setLayoutY(1200);  // setarea pozitiei pe axa OY de unde va fi afisat textField-ul
	        name.setMaxWidth(178);  // latimea maxima a textField-ului
	        name.setEditable(false);  // valorile afisate in textField nu se pot modifica
	        
	        PasswordField password = new PasswordField();  // declarare textField pentru parola
	        password.setPromptText("Get your password"); // setarea unui placeholder in lipsa afisarii de valori in textField
	        password.setVisible(true);  // setare vizibilitate textField
	        password.setLayoutX(50);  // setarea pozitiei pe axa OX de unde va fi afisat textField-ul
	        password.setLayoutY(1300); // setarea pozitiei pe axa OY de unde va fi afisat textField-ul
	        password.setMaxWidth(178);  // latimea maxima a textField-ului
	        password.setEditable(false);  // valorile afisate in textField nu se pot modifica
	        
	        ToolBar toolBar = new ToolBar();   // declarare toolBar
	        Button button = new Button("Delete file from table");  // declarare buton si numele acestuia
	        toolBar.getItems().add(button);   // adaugarea butonului in toolBar
	        toolBar.setOrientation(Orientation.HORIZONTAL);  // setarea orientarii toolBar-ului la orizontala
	        
	        button.setOnAction(new EventHandler<ActionEvent>() {   // actiunea butonului din toolBar
	            @Override public void handle(ActionEvent e) {
	            	MyFileCollection selectedItems = (MyFileCollection) tableView.getSelectionModel().getSelectedItems().get(0);   // indicele elementului selectat din tabel
	            	tableView.getItems().remove(selectedItems);  // sterge elementul selectat din tabel
	            }
	        });
	        
	         
	        TreeView treeView = new TreeView();    // declarare treeView
	        TreeItem rootItem1 = new TreeItem("Low sized files");   // declarare nod din treeView
	        TreeItem rootItem2 = new TreeItem("Medium sized files");   // declarare nod din treeView
	        TreeItem rootItem3 = new TreeItem("Large sized files");   // declarare nod din treeView
	        
	        treeView.setMaxWidth(400);   // latimea maxima a treeView-ului
	        treeView.setMaxHeight(200);  // inaltimea maxima a treeView-ului
	      
	        TreeItem rootItem = new TreeItem("Files information");   // declarare nod radacina din treeVIew
	        treeView.setRoot(rootItem);  // setare nod radacina
	      
	        for (int i = 0; i < listOfFiles.length; i++) {  // parcurgerea fisierelor din folderul "src/img/"
	        	  if (listOfFiles[i].length()/1024 < 5) 	// daca dimensiunea in KB a fisierului este mai mica de 5
	        	  {
	        		  rootItem1.getChildren().add(new TreeItem(listOfFiles[i].getName()));  // atunci este adaugat la nodul 1 ("Low sized files")
	        	  }
	        	  if (listOfFiles[i].length()/1024 >=5 && listOfFiles[i].length()/1024 < 25)   // daca dimensiunea in KB a fisierului este intre 5 si 25 
	        	  {
	        		  rootItem2.getChildren().add(new TreeItem(listOfFiles[i].getName()));  // atunci este adaugat la nodul 2 ("Medium sized files")
	        	  }
	        	  if (listOfFiles[i].length()/1024 >=25)  // daca dimensiunea in KB a fisierului este peste 25
	        	  {
	        		  rootItem3.getChildren().add(new TreeItem(listOfFiles[i].getName())); // atunci este adaugat la nodul 3 ("Large sized files")
	        	  }
	        	  
	        }
	        
	        rootItem.getChildren().add(rootItem1);  // adaugarea nodului 1 la nodul radacina
	        rootItem.getChildren().add(rootItem2);  // adaugarea nodului 2 la nodul radacina
	        rootItem.getChildren().add(rootItem3);  // adaugarea nodului 3 la nodul radacina
	        
	        Image image = new Image(getClass().getResourceAsStream("/icons/user.png"));  // variabila imagine reprezentata de imaginea stocata la "/icons/user.png"
	        Image logo = new Image(getClass().getResourceAsStream("/icons/photo.png"));  // variabila imagine reprezentata de imaginea stocata la "/icons/photo.png" 
	        Label graphicLabel = new Label("");   // declarare label initial lipsit de text
	        graphicLabel.setGraphic(new ImageView(logo));  // asocierea labelului cu o imagine in loc de text
	        
	        Button button1 = new Button("Show user credentials");   // declarare buton si text asociat
	        button1.setGraphic(new ImageView(image));   // setare buton grafic cu ajutorul unei imagini
	        button1.setOnAction(new EventHandler<ActionEvent>() {   // actiune buton
	            @Override public void handle(ActionEvent e) {
	            	name.setText(LoginController.userName);   // completarea primului textField cu continutul variabilei userName din clasa LoginController (este variabila publica)
	            	password.setText(LoginController.passWord);   // completarea primului textField cu continutul variabilei passWord din clasa LoginController (este variabila publica)
	            }
	        });
	        
	        button1.setLayoutX(50);  //  setarea pozitiei pe axa OX de unde va fi afisat butonul
	        button1.setLayoutY(800);  //  setarea pozitiei pe axa OY de unde va fi afisat butonul
	        graphicLabel.setLayoutX(80);  //  setarea pozitiei pe axa OX de unde va fi afisat labelul grafic
	        graphicLabel.setLayoutY(110);  //  setarea pozitiei pe axa OY de unde va fi afisat labelul grafic
	        
	    
	        final VBox vbox = new VBox();  // declarare frame
	        vbox.setSpacing(5);   // spatierea elementelor din frame
	        vbox.setPadding(new Insets(10, 0, 0, 10));  // adaugare de spatii fata de marginile frame-ului
	        vbox.getChildren().addAll(toolBar);  // adaugarea elementului toolBar in frame 
	        vbox.getChildren().addAll(tableView);  // adaugarea elementului tableView in frame
	        vbox.getChildren().addAll(button1);  // adaugarea elementului button1 in frame
	        vbox.getChildren().addAll(name);   // adaugarea elementului name in frame
	        vbox.getChildren().addAll(password);  // adaugarea elementului password in frame
	        vbox.getChildren().addAll(graphicLabel);   // adaugarea elementului graphicLabel in frame
	        vbox.getChildren().addAll(treeView);  // adaugarea elementului treView in frame
	        
	        
	        
	        ((StackPane) scene.getRoot()).getChildren().addAll(vbox);  // adaugarea tuturor elementelor in container

	        
	    }
	 
	 
}

