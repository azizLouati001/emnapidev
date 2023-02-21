/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.prescription;
import services.Crudpresc;
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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class presController implements Initializable {

    
    
    
    @FXML
    private AnchorPane scene1;
    @FXML
    private Label label1;
    @FXML
    private Button prescMod;
    @FXML
    private Button prescadd;
    @FXML
    private Button prescsu;
     @FXML
    private TextField idnom;
     @FXML
    private TextField iddose;
    @FXML
    private TextField idsig; 
    @FXML
   
    private TableView<prescription> idtab2;
    @FXML
    private TableColumn<prescription, String> nomCol;
    @FXML
    private TableColumn<prescription, Integer> doseCol;
    @FXML
    private TableColumn<prescription, String> sigCol;
    @FXML
 
    private ImageView idImg;
    
@Override
   public void initialize ( URL url, ResourceBundle rb){
   idnom.setText(null);
   iddose.setText(null);
   idsig.setText(null);   
   majTable();
   }
           
   public void majTable() {
    nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_medic"));
    doseCol.setCellValueFactory(new PropertyValueFactory<>("dosage"));
    sigCol.setCellValueFactory(new PropertyValueFactory<>("signature"));
    
    
    Crudpresc pres = new Crudpresc();
    List<prescription> arr = pres.showpresc();
    ObservableList<prescription> list = FXCollections.observableArrayList(arr);
        
    idtab2.setItems(list);
}
   
@FXML
private void prescAdd(ActionEvent event) {
    if (idnom.getText() == null || iddose.getText() == null || idsig.getText() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Création");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    } else {
        String aidnom = idnom.getText();
        String aiddose1 = iddose.getText();
        String aidsig = idsig.getText();

        int aiddose;
        try {
            aiddose = Integer.parseInt(aiddose1);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La dose doit être un nombre entier !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Création");
        alert.setHeaderText(null);
        alert.setContentText("Prescription ajoutée !");
        alert.show();

        prescription a = new prescription(aidnom, aiddose, aidsig);
        Crudpresc medc = new Crudpresc();
        medc.addprescription(a);
        majTable();
    }
}



      @FXML
    private void prescmodif(ActionEvent event) {
    if (idsig.getText() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Prescription");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une signature existante !");
        alert.showAndWait();
    }if ( iddose.getText() == null || idnom.getText() == null ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("prescription");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs pour modifier la prescription !");
        alert.showAndWait();
        } else {
        Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
        alertWE2.setTitle("Modification prescription");
        alertWE2.setHeaderText("Voulez-vous confirmer la modification ?");
        alertWE2.setContentText("Quelques données de la prescription vont être modifiées.");
        Optional<ButtonType> result = alertWE2.showAndWait();

        if (result.get() == ButtonType.OK) {
            String bidnom = idnom.getText();
            String biddose1 = iddose.getText();
            String bidsig = idsig.getText();

            int biddose;
            try {
                biddose = Integer.parseInt(biddose1);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La dose doit être un nombre entier !");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Prescription");
            alert.setHeaderText(null);
            alert.setContentText("Prescription modifiée !");
            alert.show();

            prescription a = new prescription(bidnom, biddose, bidsig);
            Crudpresc cda = new Crudpresc();
            cda.editprescription(a);
            majTable();
        }
    }
}

    @FXML
    private void prescDel(ActionEvent event) { 
        if (idsig.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("prescription");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une signature !");
            alert.show();

        } else { 
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression prescription");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("La prescription va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        String dID1 = idsig.getText();

         
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("conseil");
                alert.setHeaderText(null);
                alert.setContentText("prescription supprimée!");
                alert.show();

        Crudpresc cda = new Crudpresc();
        cda.deleteprescription(dID1);
        majTable();
        
            }

    }
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   