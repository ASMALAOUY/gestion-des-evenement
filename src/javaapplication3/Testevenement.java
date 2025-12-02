/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import service.EvenementService;
import entities.Evenement;
import dao.IDao;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 *
 * @author X1 YOGA
 */
public class Testevenement {
    
    public static void  main(String[] args) throws ParseException{
        EvenementService evenementService =new EvenementService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateEvent = sdf.parse("2025-01-12");
        dateEvent = sdf.parse("2020-01-12");
       //evenement.create(new Evenement("Rami","safi", dateEvent));
     // evenementService.create(new Evenement("BR","taza", dateEvent,20));
       // evenementService.create(new Evenement("BR","safi", dateEvent,70));
       // evenementService.create(new Evenement("tt","tanger", dateEvent,50));
          
        
       //Evenement evt = evenementService.findById(17);
       // evenementService.delete(evt);
        //List<Evenement> e=evenementService.findByLieu("taza");
        //System.out.println(e);
       
        // Date date = new Date(); // aujourd'hui
        //List<Evenement> evenements =evenementService.findByDate(dateEvent);
         //List<Evenement> eve =evenementService.findByDate(date);
         
         //System.out.println("Les événements à la date : " + sdf.format(date) + " est: " + eve);
         // System.out.println("Les événements à la date : " + sdf.format(dateEvent) + " est: " + evenements+"\n");
          
     //  evenementService.update(new Evenement(29, "tt", "casa", dateEvent, 50));
        for(Evenement et :evenementService.findAll())
         System.out.println(et);
    }
     
    
    
    
}
