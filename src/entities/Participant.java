/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author X1 YOGA
 */
public class Participant {
    private int id_participant;
    private String nom;
    private String email;
    private String organisation;

    public Participant(int id_participant, String nom, String email, String organisation) {
        this.id_participant = id_participant;
        this.nom = nom;
        this.email = email;
        this.organisation = organisation;
    }

    public Participant(String nom, String email, String organisation) {
        this.nom = nom;
        this.email = email;
        this.organisation = organisation;
    }

    public Participant() {
    }

    public int getId_participant() {
        return id_participant;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    
    
    @Override
  public String toString() {
    return nom;
}
}
