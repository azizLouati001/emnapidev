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
public class medicaments {
    private String  dci;
    private  String nomComercial ;
    private  int disponibilite ;
    private float prix;

    public medicaments(String dci, String nomComercial, int disponibilite, float prix) {
        this.dci = dci;
        this.nomComercial = nomComercial;
        this.disponibilite = disponibilite;
        this.prix = prix;
    }

    public String getDci() {
        return dci;
    }

    public void setDci(String dci) {
        this.dci = dci;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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
        final medicaments other = (medicaments) obj;
        if (this.disponibilite != other.disponibilite) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.dci, other.dci)) {
            return false;
        }
        if (!Objects.equals(this.nomComercial, other.nomComercial)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "medicaments{" + "dci=" + dci + ", nomComercial=" + nomComercial + ", disponibilite=" + disponibilite + ", prix=" + prix + '}';
    }
   
}
