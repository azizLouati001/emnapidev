/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class prescription {
    private  String nom_medic;
    private  int dosage;
    private  String signature;



    public prescription(String nom_medic, int dosage, String signature) {
        this.nom_medic = nom_medic;
        this.dosage = dosage;
        this.signature = signature;
    }
        public String getNom_medic() {
        return nom_medic;
    }

    public void setNom_medic(String nom_medic) {
        this.nom_medic = nom_medic;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final prescription other = (prescription) obj;
        if (this.dosage != other.dosage) {
            return false;
        }
        if (!Objects.equals(this.nom_medic, other.nom_medic)) {
            return false;
        }
        if (!Objects.equals(this.signature, other.signature)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prescription{" + "nom_medic=" + nom_medic + ", dosage=" + dosage + ", signature=" + signature + '}';
    }
    
}
