/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexion.Connexion;
import dao.IDao;
import entities.Evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X1 YOGA
 */
public class EvenementService implements IDao<Evenement >{

    @Override
    public boolean create(Evenement o) {
          try {
            String req = "INSERT INTO evenement(titre, lieu, dateEv,capacite) VALUES (?, ?, ?,?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getLieu());
            ps.setDate(3, new java.sql.Date(o.getDateEv().getTime()));
            ps.setInt(4, o.getCapacite());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean update(Evenement o) {
         try {
            String req = "UPDATE `evenement` SET `titre` = ?, `lieu` = ?, `dateEv` = ?, `capacite` = ? WHERE `evenement`.`id_evenement` = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getLieu());
            ps.setDate(3, new java.sql.Date(o.getDateEv().getTime()));
            ps.setInt(4, o.getCapacite());
            ps.setInt(5, o.getId_evenement());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
 }

    @Override
    public boolean delete(Evenement o) {
        try{
        String req="DELETE FROM evenement WHERE id_evenement = ?" ;
         PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId_evenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public Evenement findById(int id) {
         Evenement e=null;
         String req = "SELECT * FROM evenement where `id_evenement` =?";
         try{
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
            e = new Evenement(
                rs.getInt("id_evenement"),
                rs.getString("titre"),
                rs.getString("lieu"),
                rs.getDate("dateEv"),
                rs.getInt("capacite")
            );
        }
              return e;
             
         }catch(SQLException ex){
              Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE,null,ex);
               return e;
         }
        
    }

   @Override
public List<Evenement> findAll() {
    List<Evenement> evenements = new ArrayList<>();
    String req = "SELECT * FROM evenement";

    try {
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            evenements.add(new Evenement(
                 rs.getInt("id_evenement"),
                rs.getString("titre"),
                rs.getString("lieu"),
                rs.getDate("dateEv"),
                rs.getInt("capacite")));
        }

    } catch (SQLException ex) {
        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return evenements;
}

public List<Evenement> findByLieu(String lieu) {
    List<Evenement> evenements = new ArrayList<>();
    try {
        String req = "SELECT * FROM evenement WHERE lieu LIKE ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, "%" + lieu + "%");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            evenements.add(new Evenement(
                rs.getInt("id_evenement"),
                rs.getString("titre"),
                rs.getString("lieu"),
                rs.getDate("dateEv"),
                rs.getInt("capacite")
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return evenements;
}

public List<Evenement> findByDate(Date date) {
    List<Evenement> evenements = new ArrayList<>();
    try {
        String req = "SELECT * FROM evenement WHERE DATE(dateEv) = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            evenements.add(new Evenement(
                rs.getInt("id_evenement"),
                rs.getString("titre"),
                rs.getString("lieu"),
                rs.getDate("dateEv"),
                rs.getInt("capacite")
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return evenements;
}

}
