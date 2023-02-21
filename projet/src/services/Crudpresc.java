/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.prescription;
import tools.myCnx;

public class Crudpresc {
        public void addprescription(prescription a) {
        try {
            String requete = "INSERT INTO prescription (nom_medic,dosage,signature)" + "VALUES (?,?,?)";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
          
            pst.setString(1, a.getNom_medic());
            pst.setInt(2, a.getDosage());
            pst.setString(3, a.getSignature());          
            pst.executeUpdate();

            System.out.println("prescription ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<prescription> showpresc() {
        ObservableList<prescription> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT nom_medic, dosage, signature FROM prescription";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                list.add(new prescription(rs.getString("nom_medic"),rs.getInt("dosage"),rs.getString("signature")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void editprescription(prescription a) {
    try {
        String requete = "UPDATE prescription SET nom_medic = ?, dosage = ? WHERE signature = ?";
        PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
        pst.setString(1, a.getNom_medic());
        pst.setInt(2, a.getDosage());
        pst.setString(3, a.getSignature()); // set the signature parameter
        
        pst.executeUpdate();

        System.out.println("prescription modifiée!");

    } catch (SQLException ex) {
        Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
    }
}

        public void deleteprescription(String signature) {

        try {
            String requete = "DELETE FROM prescription WHERE signature = ?";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
                    
            pst.setString(1, signature);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
