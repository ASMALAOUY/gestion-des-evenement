/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexion.Connexion;
import dao.IDao;
import entities.Evenement;
import entities.Inscription;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author X1 YOGA
 */
public class InscriptionService implements IDao<Inscription> {
    
    private EvenementService Et;
    private ParticipantService Pt;

    public InscriptionService() {
        Et = new EvenementService();
        Pt = new  ParticipantService();
    }

    @Override
    public boolean create(Inscription o) {
        try {
            String req = "INSERT INTO `inscription` (`id`, `id_evenement`, `id_participant`, `dateInscription`) VALUES (NULL, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getEvenement().getId_evenement());
            ps.setInt(2, o.getParticipant().getId_participant());
            ps.setDate(3, new java.sql.Date(o.getDateInscription().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Inscription o) {
         try {
            String req = "UPDATE inscription SET dateInscription = ?, id_participant = ?, id_evenement = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setDate(1, new java.sql.Date(o.getDateInscription().getTime()));
            ps.setInt(2, o.getParticipant().getId_participant());
            ps.setInt(3, o.getEvenement().getId_evenement());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Inscription o) {
        try {
            String req = "DELETE  from inscription WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Inscription findById(int id) {
       Inscription e=null;
        try {
            
            String req = "select * from inscription where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 return new Inscription(rs.getInt("id"), Et.findById(rs.getInt("id_evenement")),Pt.findById(rs.getInt("id_participant")) ,rs.getDate("dateInscription"));
                                                                                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Inscription> findAll() {
         List<Inscription> inscriptions = new ArrayList<>();
        try {
            String req = "select * from inscription";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscriptions.add(new Inscription(rs.getInt("id"), Et.findById(rs.getInt("id_evenement")),Pt.findById(rs.getInt("id_participant")) ,rs.getDate("dateInscription")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscriptions;
    }


    public int countByEvenement(int idEvenement) {
         try {
        String req = "SELECT COUNT(*) AS total FROM inscription WHERE id_evenement = ?";;
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, idEvenement);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0;
    }
    public int tauxRemplissage(int idEvenement) {
    try {
        
        Evenement evenement = Et.findById(idEvenement);
        
        if (evenement == null) {
            System.out.println("Evenement introuvable");
            return 0;
        }
        
        int capacite = evenement.getCapacite();
        
        
        if (capacite == 0) {
            System.out.println("Capacité de l'evenement est 0");
            return 0;
        }
        

        int nombreInscriptions = countByEvenement(idEvenement);
        
        
        int tauxRemplissage = (nombreInscriptions * 100) / capacite;
        
        return tauxRemplissage;
        
    } catch (Exception ex) {
        Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
    }
}
public Map<String, Integer> getParticipantsParEvenement() {
    Map<String, Integer> data = new HashMap<>();
    try {
        String req = "SELECT e.titre, COUNT(i.id) as nb_participants " +
                     "FROM evenement e " +
                     "LEFT JOIN inscription i ON e.id_evenement = i.id_evenement " +
                     "GROUP BY e.id_evenement, e.titre";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            data.put(rs.getString("titre"), rs.getInt("nb_participants"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
}

    
}
