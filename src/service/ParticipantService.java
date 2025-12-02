/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexion.Connexion;
import dao.IDao;
import entities.Evenement;
import entities.Participant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X1 YOGA
 */
public class ParticipantService implements  IDao<Participant>{

    @Override
    public boolean create(Participant o) {
        try {
            String req = "INSERT INTO participant(nom, email, organisation) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setString(3, o.getOrganisation());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    @Override
    public boolean update(Participant o) {
         try {
            String req = "UPDATE `participant` SET `nom` = ?, `email` = ?, `organisation` = ? WHERE `participant`.`id_participant` = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setString(3, o.getOrganisation());
            ps.setInt(4, o.getId_participant());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Participant o) {
     try{
        String req="DELETE FROM participant WHERE id_participant = ?" ;
         PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId_participant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Participant findById(int id) {
        Participant p=null;
         String req = "SELECT * FROM participant where `id_participant` =?";
         try{
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
             if(rs.next()){
                p=new Participant(
                rs.getInt("id_participant"),
                rs.getString("nom"),
                rs.getString("email"),
                rs.getString("organisation") );
             }
              return p;
             
         }catch(SQLException ex){
              Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE,null,ex);
               return p;
         }
    }

    @Override
    public List<Participant> findAll() {
         List<Participant> participants = new ArrayList<>();
         String req = "SELECT * FROM participant";

    try {
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            participants.add(new Participant(
                rs.getInt("id_participant"),
                rs.getString("nom"),
                rs.getString("email"),
                rs.getString("organisation")));
            
        }
        

    } catch (SQLException ex) {
        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return participants;
}
}



