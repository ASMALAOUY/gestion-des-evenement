/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import entities.Participant;
import service.ParticipantService;

/**
 *
 * @author X1 YOGA
 */
public class Testparticipant {
    public static void  main(String[] args) {
       ParticipantService participantService =new ParticipantService();
       /*
       participantService.create(new Participant("asma","asma@gmail.com", "soft"));
       participantService.create(new Participant("salma","salma@gmail.com", "RMS"));
       participantService.create(new Participant("hind","hind@gmail.com", "lv"));
         */ 
       
       Participant part = participantService .findById(7);
         participantService .delete(part);
         
        for(Participant p :participantService .findAll())
            System.out.println(p);
             
    }
    
}
