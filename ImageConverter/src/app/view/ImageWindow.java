package app.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageWindow {
	
	@FXML
	private Tab Label1;   
	
	@FXML
	private Tab Label2;

    @FXML
    private ListView<String> listView;   // listView in care vor fi afisate fisierele selectate
    
    @FXML
    private RadioButton rb_single;		// radioButton pentru a selecta optiunea de a alege un singur fisier

    @FXML
    private ToggleGroup Group;			// numele grupului din care fac parte cele doua radioButtons

    @FXML
    private RadioButton rb_multiple;		// radioButton pentru a selecta optiunea de a alege mai multe fisiere
    
    @FXML
    private ImageView imageView;		// container pentru afisarea de imagini prelucrate

    @FXML
    private ChoiceBox<String> choiceBox;	// buton din care se poate ale una dintre imaginile prelucrate
    
    @FXML
    private Label label;
    
    @FXML
    private ProgressBar progressBar;		// progressBar care se incarca cat timp este afisata imaginea prelucrata in imageView
    
    @FXML
    private Label progressLabel;		// label cu mesajul "Loaded" care este afisata cand progressBar-ul este incarcat complet
    
    @FXML
    private Button convertButton;		// butonul pentru conversia de imagine
    
    @FXML
    public void initialize() {  
    	convertButton.setStyle("-fx-background-color: #2cc7bf;");    // setarea culorii pentru butonul de conversie a imaginii
    }

    @FXML
    void onChooseClick(ActionEvent event) {                        // actiunea lansata de apasarea butonului
    	if(!rb_single.isSelected() && !rb_multiple.isSelected()) 	// daca niciunul din grupul de radiobuttons nu este selectat
    	{
    		Alert alert = new Alert(AlertType.WARNING, "You haven't chosen a radio button!");   // declarare alerta de warning cu mesaj
        	alert.showAndWait();		// afisare pana cand utilizatorul apasa Close
    	}
    	else
    	{
    		if(rb_single.isSelected())			// daca este selectata optiunea de a alege un singur buton
    		{
    			FileChooser fc = new FileChooser();		// definire variabila de tip FileChooser
    	    	fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.bmp")); // definirea extensiilor acceptate de FileChooser
    	    	File selectedFile = fc.showOpenDialog(null);	// deschidere fereastra de cautare fisiere
    	    	if(selectedFile != null)    // daca este selectat un fisier
    	    	{
    	    		listView.getItems().add(selectedFile.getAbsolutePath());   // daca este selectat un fisier, atunci calea absoluta este adaugata in listView
    	    	}
    		}
    		else                 // if(rb_multiple.isSelected()) daca este selectata optiunea de a alege mai multe butoane     
    		{
    			FileChooser fc = new FileChooser();    // definire variabila de tip FileChooser
    	    	fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.bmp"));  // definirea extensiilor acceptate de FileChooser
    	    	List<File> selectedFile = (List<File>) fc.showOpenMultipleDialog(null);  // deschidere fereastra de cautare fisiere
    	    	if(selectedFile != null)   // daca este selectat cel putin un fisier
    	    	{
    	    		for(int i=0; i<selectedFile.size(); i++)        // parcurgere vector de fisiere
    	    		{
    	    			listView.getItems().add(selectedFile.get(i).getAbsolutePath());    // calea absoluta a fiecarui fisier selectat este adaugata in listView
    	    		}
    	    	}
    		}
    	}
    }
    
    @FXML
    void onDeleteClick(ActionEvent event) {    // actiunea de stergere a unui rand din listView
    	final int selectedIdx = listView.getSelectionModel().getSelectedIndex();    // indicele fisierului selectat prin click pe randul corespunzator din listView
        if (selectedIdx != -1) {		// daca a fost selectat un rand
         
          final int newSelectedIdx =	// randul care va fi selectat dupa stergerea randului curent
            (selectedIdx == listView.getItems().size() - 1)   // noul rand va fi indicele randului curent minus 1 daca randul curent selectat nu este primul
               ? selectedIdx - 1
               : selectedIdx;								  // altfel noul rand va coincide cu primul, deoarece randul sters era chiar primul
 
          listView.getItems().remove(selectedIdx);			// eliminare rand selectat din listView
          listView.getSelectionModel().select(newSelectedIdx);   // selectarea noului rand dupa stergere
        }
        else
        {
        	Alert alert = new Alert(AlertType.ERROR, "You haven't selected one item to remove!");   // daca nu este selectat un fisier atunci se afiseaza o alerta care sa specifice acest lucru
        	alert.showAndWait();		//   alerta se inchide la apasarea butinului Close
        }
    }
    
    @FXML
    void onConvertClick(ActionEvent event) throws IOException {	    // actiunea de conversie imagine (nu tine de interfata vizuala)

    	List<String> items = listView.getItems();
    	for (String each: items)
        {
    		BufferedImage img = null;
    	    File file = null;
    	    try
    	    {
    	        file = new File(each);
    	        img = ImageIO.read(file);
    	    }
    	    catch(IOException e)
    	    {
    	        e.printStackTrace();
    	    }
    	    int width = img.getWidth();
    	    int height = img.getHeight();
    	    
    	    for(int y = 0; y < height; y++){
    	        for(int x = 0; x < width; x++){
    	          int p = img.getRGB(x,y);

    	          int a = (p>>24)&0xff;
    	          int r = (p>>16)&0xff;
    	          int g = (p>>8)&0xff;
    	          int b = p&0xff;

    	          //calculate average
    	          int avg = (r+g+b)/3;

    	          //replace RGB value with avg
    	          p = (a<<24) | (avg<<16) | (avg<<8) | avg;

    	          img.setRGB(x, y, p);
    	        }
    	      }
    	    
    	    try
    	    {
    	        file = new File("src/img/"+file.getName().replaceFirst("[.][^.]+$", "")+".jpg");
    	        choiceBox.getItems().add("/img/"+file.getName());            // choiceBox-ul este incarcat cu numele fisierelor prelucrate care sunt stocate in folderul "img"
    	        ImageIO.write(img, "jpg", file);
    	    }
    	    catch(IOException e)
    	    {
    	        e.printStackTrace();
    	    }
        }

    }
    
    @FXML
    void onShowClick(ActionEvent event) {    // actiunea de afisare de imagini prelucrate
    	if(choiceBox.getValue() != null)      // daca se alege o valoare din choiceBox-ul cu numele fisierelor prelucrate
    		{
    			int STARTTIME = 2000;		// timp de incarcare de 2 secunde
    			IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME); // declararea unei variabile care stocheaza durata de timp  
    			Image image = new Image(choiceBox.getValue());   // declararea imaginii selectate din checkBox
    			imageView.setImage(image);			// afisarea imaginii
    			progressBar.progressProperty().bind(timeSeconds.divide(STARTTIME*1.0));  // incarcarea progressBar-ului in 2 secunde  (se face raportul dintre durata de timp de la apasarea butonului si timpul predefinit de incarcare care este 1 cand progressBar-ul este complet)
    			progressLabel.setText("Loaded");   // afisarea labelului cu continutul "Loaded"
    				
    		}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR, "You haven't selected a converted image!");   // daca nu este aleasa o valoare din choiceBox se afiseaza o alerta
        	alert.showAndWait();    // inchidere la apasarea butonului Close
    	}
    	
    }
    
    @FXML
    void onLinkClick(ActionEvent event) {    // actiune la apasarea hyperlinkului
    	label.setText("Hyperlink visited");   // labelul de sub hyperlink se modifica din "Hyperlink not visited" in "Hyperlink visited"
    }
    
    @FXML
    void onViewClick(ActionEvent event) throws Exception {   // actiunea de deschidere a interfetei realizata numai din cod Java
		
		Stage stage = new Stage();    // declararea unei variabile de tip Stage
        stage.setTitle("Image Result");   // setarea titlului interfetei
        ImageResult imgResult = new ImageResult();  // lansarea in executie a interfetei specificate de clasa ImageResult 
    	imgResult.start(stage);   // vizualizarea interfetei
    }

}
