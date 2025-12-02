/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import entities.Inscription;
import java.util.Date;
import java.util.Map;
import service.EvenementService;
import service.InscriptionService;
import service.ParticipantService;

/**
 *
 * @author X1 YOGA
 */
public class Testinscriptions {
    public static void main(String[] args) {
     
    InscriptionService insc=new InscriptionService();
    EvenementService evt=new EvenementService();
    ParticipantService part=new ParticipantService();
    /*
    insc.create(new Inscription(evt.findById(18) ,part.findById(8),new Date()));
    insc.create(new Inscription(evt.findById(18) ,part.findById(9),new Date()));
    insc.create(new Inscription(evt.findById(18) ,part.findById(8),new Date()));
    insc.create(new Inscription(evt.findById(18) ,part.findById(9),new Date()));
            */
   // insc.create(new Inscription(evt.findById(19) ,part.findById(8),new Date()));
    //insc.create(new Inscription(evt.findById(20) ,part.findById(9),new Date()));
    
    //Inscription in=insc.findById(3);
     //   insc.delete(in);
    //Inscription in=insc.findById(2);
    //    insc.delete(in);
        
    //Inscription in=insc.findById(3);
      //  insc.delete(in);
   
    for(Inscription e:insc.findAll())
         
        System.out.println(e);
    
  
   int nbInscriptions = insc.countByEvenement(20);
    
   
        System.out.println("Nombre d'inscriptions: " + nbInscriptions);
        
        // Afficher le taux de remplissage
        int taux = insc.tauxRemplissage(20);
        System.out.println("Taux de remplissage: " + taux + "%");
          System.out.println("\n? Graphique - Participants par événement :");
        Map<String, Integer> graphique = insc.getParticipantsParEvenement();
    System.out.println(graphique);
         
    
    }
  
    
    
}
