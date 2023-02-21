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
import entities.medicaments;
import tools.myCnx;

public class Crudmedc {
    public void addmedicaments(medicaments a) {
        try {
            String requete = "INSERT INTO medicaments (dci,nomComercial,disponibilite,prix)" + "VALUES (?,?,?,?)";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
          
            pst.setString(1, a.getDci());
            pst.setString(2, a.getNomComercial());
            pst.setInt(3, a.getDisponibilite());          
            pst.setFloat(4, a.getPrix());
            pst.executeUpdate();

            System.out.println("medicaments ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public ObservableList<medicaments> showmedic() {
        ObservableList<medicaments> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM medicaments";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                list.add(new medicaments(rs.getString("dci"),rs.getString("nomComercial"),rs.getInt("disponibilite"), rs.getFloat("prix")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void editmedicaments(medicaments a) {
    try {
        String requete = "UPDATE medicaments SET nomComercial = ?, disponibilite = ?, prix = ? WHERE dci = ?";
        PreparedStatement pst = new myCnx().cn.prepareStatement(requete);

        pst.setString(1, a.getNomComercial());
        pst.setInt(2, a.getDisponibilite());
        pst.setFloat(3, a.getPrix());
        pst.setString(4, a.getDci());

        pst.executeUpdate();
        System.out.println("medicaments modifiée!");
    } catch (SQLException ex) {
        Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
    }
}

        public void deletemedicaments(String dci) {

        try {
            String requete = "DELETE FROM medicaments WHERE dci = ?";
            PreparedStatement pst = new myCnx().cn.prepareStatement(requete);
                    
            pst.setString(1, dci);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crudmedc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
