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
public class Inscription {
    private int id;
    private Evenement  evenement;
    private Participant participant;
    private Date dateInscription;

    public Inscription(int id, Evenement evenement, Participant participant, Date dateInscription) {
        this.id = id;
        this.evenement = evenement;
        this.participant = participant;
        this.dateInscription = dateInscription;
    }

    public Inscription(Evenement evenement, Participant participant, Date dateInscription) {
        this.evenement = evenement;
        this.participant = participant;
        this.dateInscription = dateInscription;
    }

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", evenement=" + evenement + ", participant=" + participant + ", dateInscription=" + dateInscription + '}';
    }
    
    
    
}
