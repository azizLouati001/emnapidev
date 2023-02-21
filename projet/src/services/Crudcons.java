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
import entities.conseil;
import tools.myCnx;

public class Crudcons {
    public void addcons(conseil a) {
        try {
            String requete = "INSERT INTO conseil (id, titre, description) VALUES (?, ?, ?)";

            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
          
            pst.setString(1, a.getId());
            pst.setString(2, a.getTitre());
            pst.setString(3, a.getDescription());
            pst.executeUpdate();

            System.out.println("conseil ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(Crudcons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<conseil> showcons() {
        ObservableList<conseil> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM conseil ";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                list.add(new conseil(rs.getString("id"),rs.getString("titre"),rs.getString("description")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Crudcons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void editcons(conseil a) {
    try {
        String requete = "UPDATE conseil SET titre = ?, description = ? WHERE id = ?";
        PreparedStatement pst = new myCnx().cn.prepareStatement(requete);

        pst.setString(1, a.getTitre());
        pst.setString(2, a.getDescription());
        pst.setString(3, a.getId());
        pst.executeUpdate();
        System.out.println("conseil modifiée!");
    } catch (SQLException ex) {
        Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        public void deletecons(String id) {

        try {
            String requete = "DELETE FROM conseil WHERE id = ?";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
                    
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
