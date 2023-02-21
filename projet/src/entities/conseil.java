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
public class conseil {
    
    private String id; // unique ID for each advice
    private String titre;
    private String description;

    public conseil(String id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        conseil that = (conseil) obj;
        return id == that.id &&
                Objects.equals(titre, that.titre) &&
                Objects.equals(description, that.description);
    }

    @Override
    public String toString() {
        return "HealthAdvice{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    
}
