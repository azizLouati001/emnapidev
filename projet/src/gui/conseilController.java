/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.conseil;
import services.Crudcons;
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
public class conseilController implements Initializable {

    
    
    
    @FXML
    private AnchorPane scene1;
    @FXML
    private Label label1;
    @FXML
    private Button consMod;
    @FXML
    private Button consadd;
    @FXML
    private Button conssu;
     @FXML
    private TextField idRef;
     @FXML
    private TextField idTit;
    @FXML
    private TextArea idCont; 
    @FXML
   
    private TableView<conseil> idtab1;
    @FXML
    private TableColumn<conseil, String> idCol;
    @FXML
    private TableColumn<conseil, String> titreCol;
    @FXML
    private TableColumn<conseil, String> contCol;
    @FXML
 
    private ImageView idImg;
    
@Override
   public void initialize ( URL url, ResourceBundle rb){
   idRef.setText(null);
   idTit.setText(null);
   idCont.setText(null);   
   majTable();
   }
           
   public void majTable() {
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
    contCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    
    Crudcons cons = new Crudcons();
    List<conseil> arr = cons.showcons();
    ObservableList<conseil> list = FXCollections.observableArrayList(arr);
        
    idtab1.setItems(list);
}
 
   



    
    
@FXML
private void consAdd(ActionEvent event) {
    if (idRef.getText() == null ||idTit.getText() == null ||idCont.getText() == null ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("creation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.showAndWait();

    } else {
        String aidRef = idRef.getText();  
        String aidTit = idTit.getText();
        String aidCont = idCont.getText();

        // Check if id already exists in the list
        Crudcons cons = new Crudcons();
        List<conseil> arr = cons.showcons();
        boolean idExists = arr.stream().anyMatch(c -> c.getId().equals(aidRef));
        if (idExists) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("creation");
            alert.setHeaderText(null);
            alert.setContentText("L'id doit être unique!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("conseil ajoutée!");
            alert.show();

            conseil a = new conseil(aidRef,aidTit,aidCont);
            Crudcons medc = new Crudcons();
            medc.addcons(a); 
            majTable();
        }
    }
}

      @FXML
    private void consmodif(ActionEvent event) {
        if (idRef.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("conseil");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un id existant !");
            alert.showAndWait();

        }if (idTit.getText() == null || idCont.getText() == null ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("conseil");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs pour modifier l'article !");
        alert.showAndWait();
        }
        else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification conseil");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText("Quelques donnés du conseil vont être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
        
        
        
        
        String bidRef = idRef.getText();  
        String bidTit = idTit.getText();
        String bidCont = idCont.getText();
 
        
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("conseil");
                alert.setHeaderText(null);
                alert.setContentText("conseil modifiée!");
                alert.show();

        conseil a = new conseil(bidRef, bidTit,bidCont);
        Crudcons cda = new Crudcons();
        cda.editcons(a);
        majTable();
        
  
                
            }
    }
    }
    @FXML
    private void consDel(ActionEvent event) { 
        if (idRef.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("conseil");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un ID!");
            alert.show();

        } else { 
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression conseil");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("La conseil va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        String dID1 = idRef.getText();

         
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("conseil");
                alert.setHeaderText(null);
                alert.setContentText("conseil supprimée!");
                alert.show();

        Crudcons cda = new Crudcons();
        cda.deletecons(dID1);
        majTable();
        
            }

    }
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   