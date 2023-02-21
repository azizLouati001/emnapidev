/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.medicaments;
import services.Crudmedc;
;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class EspaceMedecinController implements Initializable {

    
    
    
    @FXML
    private AnchorPane scene1;
    @FXML
    private Label label1;
    @FXML
    private Button idmodif;
    @FXML
    private Button idadd;
    @FXML
    private Button iddel;
     @FXML
    private Button idaff;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TextField iddci;
     @FXML
    private TextField idcomerc;
    @FXML
    private TextField iddispo; 
    @FXML
    private TextField idprix; 
    @FXML
    private TableView<medicaments> idtab;
    @FXML
    private TableColumn<medicaments, String> idcoldci;
    @FXML
    private TableColumn<medicaments, String> idcolcomerc;
    @FXML
    private TableColumn<medicaments, Integer> idcoldispo;
    @FXML
    private TableColumn<medicaments, Float> idcolprix;
    @FXML
    private ImageView idImg;
    
@Override
   public void initialize ( URL url, ResourceBundle rb){
   iddci.setText(null);
   idcomerc.setText(null);
   iddispo.setText(null);
   idprix.setText(null);
   majTable();
   }
           
   public void majTable() {
    idcoldci.setCellValueFactory(new PropertyValueFactory<>("dci"));
    idcolcomerc.setCellValueFactory(new PropertyValueFactory<>("nomComercial"));
    idcoldispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
    idcolprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    
    Crudmedc med = new Crudmedc();
    List<medicaments> arr = med.showmedic();
    ObservableList<medicaments> list = FXCollections.observableArrayList(arr);
        
    idtab.setItems(list);
}
 
   



    
    
    @FXML
    private void AddMed(ActionEvent event) {
         if (iddci.getText() == null ||idcomerc.getText() == null ||iddispo.getText() == null || idprix.getText()==null ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("creation");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
        String aDci = iddci.getText();  
        String aComerc = idcomerc.getText();
        String aDispo1 = iddispo.getText();
        String aPrix1 = idprix.getText();
        
        Crudmedc cons = new Crudmedc();
        List<medicaments> arr = cons.showmedic();
        boolean idExists = arr.stream().anyMatch(c -> c.getDci().equals(aDci));
        if (idExists) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("creation");
            alert.setHeaderText(null);
            alert.setContentText("L'id doit être unique!");
            alert.showAndWait();
        } 
        else{
        try {
            float aPrix = Float.parseFloat(aPrix1);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre!");
            alert.showAndWait();
            return;
        }
                if (!aDispo1.equals("0") && !aDispo1.equals("1")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("La disponibilité doit être 0 ou 1!");
            alert.showAndWait();
            return;
        }
        int aDispo = Integer.parseInt(aDispo1);
        float aPrix = Float.parseFloat(aPrix1);
                
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("médicaments ajoutée!");
            alert.show();

        medicaments a = new medicaments(aDci,aComerc,aDispo, aPrix);
        Crudmedc medc = new Crudmedc();
        medc.addmedicaments(a); 
        majTable();
        }
         }
    
    } 
      @FXML
    private void EditMed(ActionEvent event) {
        if (iddci.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("médicaments");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un DCI du médicaments !");
            alert.showAndWait();

        } else  {
        if (iddci.getText() == null || idcomerc.getText() == null || iddispo.getText() == null || idprix.getText() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("médicaments");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs pour modifier le médicament !");
        alert.showAndWait();
        } else{
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification médicaments");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText("Quelques donnés du médicaments vont être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
        
        
        
        
        String bDci = iddci.getText();  
        String bComerc = idcomerc.getText();
        String bDispo1 = iddispo.getText();
        String bPrix1 = idprix.getText();
try {
            float bPrix = Float.parseFloat(bPrix1);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre!");
            alert.showAndWait();
            return;
        }
                if (!bDispo1.equals("0") && !bDispo1.equals("1")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("La disponibilité doit être 0 ou 1!");
            alert.showAndWait();
            return;
        }
        int bDispo = Integer.parseInt(bDispo1);
        float bPrix = Float.parseFloat(bPrix1); 
        
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("médicament");
                alert.setHeaderText(null);
                alert.setContentText("médicament modifiée!");
                alert.show();

        medicaments a = new medicaments(bDci, bComerc,bDispo,bPrix);
        Crudmedc cda = new Crudmedc();
        cda.editmedicaments(a);
        majTable();
        
  
                
            }
    }
    }
    }
    @FXML
    private void DeleteMed(ActionEvent event) { 
        if (iddci.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("médicaments");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un DCI!");
            alert.show();

        } else { 
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression médicament");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("Le médicament va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        String dID1 = iddci.getText();

         
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("médicament");
                alert.setHeaderText(null);
                alert.setContentText("médicament supprimée!");
                alert.show();

        Crudmedc cda = new Crudmedc();
        cda.deletemedicaments(dID1);
        majTable();
        
            }

    }
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   