/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author X1 YOGA
 */
public class Evenement {
    private int id_evenement;
    private String titre;
    private String lieu;
    private Date dateEv;
    private int capacite;

    public Evenement(int id_evenement, String titre, String lieu, Date dateEv,int capacite) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.lieu = lieu;
        this.dateEv = dateEv;
        this.capacite=capacite;
    }

    public Evenement() {
    }

    public Evenement(String titre, String lieu, Date dateEv,int capacite) {
        this.titre = titre;
        this.lieu = lieu;
        this.dateEv = dateEv;
        this.capacite=capacite;
    }
   /* public Evenement(int id_evenement,String titre, String lieu, Date dateEv) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.lieu = lieu;
        this.dateEv = dateEv;
        
        
    }
 */   

    public int getId_evenement() {
        return id_evenement;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDateEv() {
        return dateEv;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDateEv(Date dateEv) {
        this.dateEv = dateEv;
    }

   
  @Override
public String toString() {
    return titre;
}
    
    
    
}
