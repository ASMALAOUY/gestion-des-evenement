package service;

import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import javax.swing.SwingUtilities;

public class UserService {

    /**
     * Hache un mot de passe avec MD5
     */
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            
            // Convertir en hexadécimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du hachage", e);
        }
    }

    /**
     * Met à jour le mot de passe par email (HACHE le mot de passe)
     */
    public boolean updatePasswordByEmail(String email, String newPass) {
        try {
            String hashedPassword = hashPassword(newPass);
            String sql = "UPDATE utilisateur SET password = ? WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);

            ps.setString(1, hashedPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur updatePasswordByEmail : " + e.getMessage());
            return false;
        }
    }

    /**
     * Vérifie la connexion (compare les hashs)
     */
    public boolean checkLogin(String email, String password) {
        try {
            // Hacher le mot de passe saisi
            String hashedInputPassword = hashPassword(password);
            
            String sql = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, hashedInputPassword);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Erreur checkLogin : " + e.getMessage());
            return false;
        }
    }

    /**
     * Vérifie si un utilisateur existe
     */
    public boolean checkUserExists(String email) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch(Exception e) {
            System.out.println("Erreur checkUserExists : " + e.getMessage());
            return false;
        }
    }

    /**
     * Réinitialise le mot de passe et envoie un email (AVEC THREAD)
     */
    public void resetPassword(String emailTo) {

        // 1. Générer un nouveau mot de passe
        final String newPassword = "Asma@#"
                + "" + (int)(Math.random() * 9000 + 1000);

        // 2. Mettre à jour dans la base (sera HACHÉ avec MD5)
        boolean updated = updatePasswordByEmail(emailTo, newPassword);

        if (!updated) {
            JOptionPane.showMessageDialog(null,
                    "Erreur : Email introuvable dans la base !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 3. Créer et lancer le thread pour l'envoi d'email
        Thread emailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread d'envoi d'email démarré: " + Thread.currentThread().getName());
                
                // Informations email
                final String senderEmail = "a.laouy2064@uca.ac.ma";
                final String senderPassword = "grje vkkr jqfk atzg";
                final String subject = "Réinitialisation du mot de passe";
                String msg = "Bonjour,\n\nVotre nouveau mot de passe est : " 
                        + newPassword 
                        + "\n\nStudio Asma";

                try {
                    java.util.Properties props = new java.util.Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

                    javax.mail.Session session = javax.mail.Session.getInstance(
                            props,
                            new javax.mail.Authenticator() {
                                @Override
                                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                    return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
                                }
                            }
                    );

                    javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
                    message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
                    message.setRecipients(javax.mail.Message.RecipientType.TO,
                            javax.mail.internet.InternetAddress.parse(emailTo));
                    message.setSubject(subject);
                    message.setText(msg);

                    // ENVOI DE L'EMAIL (opération bloquante)
                    javax.mail.Transport.send(message);
                    
                    System.out.println("Email envoyé avec succès!");

                    // Mise à jour de l'UI sur le thread EDT
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null,
                                    "Nouveau mot de passe envoyé par email !",
                                    "Succès",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    });

                } catch (Exception e) {
                    System.err.println("Erreur lors de l'envoi: " + e.getMessage());
                    e.printStackTrace();
                    
                    // Afficher l'erreur sur le thread EDT
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null,
                                    "Erreur envoi email : " + e.getMessage(),
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });
                }
                
                System.out.println("Thread d'envoi d'email terminé");
            }
        });
        
        emailThread.setName("EmailSender-Thread");
        emailThread.start();
        
        // Message immédiat pour l'utilisateur
        JOptionPane.showMessageDialog(null,
                "Envoi de l'email en cours...",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Enregistre un nouvel utilisateur
     */
    public boolean registerUser(String email, String password, String nom, String prenom) {
        try {
            System.out.println("=== Début registerUser ===");
            System.out.println("Email: " + email);
            System.out.println("Nom: " + nom);
            System.out.println("Prénom: " + prenom);
            
            // Vérifier si l'utilisateur existe déjà
            if (checkUserExists(email)) {
                System.out.println("Email existe déjà : " + email);
                return false;
            }
            
            String hashedPassword = hashPassword(password);
            System.out.println("Mot de passe hashé : " + hashedPassword);
            
            String sql = "INSERT INTO utilisateur (email, password, nom, prenom) VALUES (?, ?, ?, ?)";
            System.out.println("SQL : " + sql);
            
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            
            int result = ps.executeUpdate();
            System.out.println("Résultat : " + result + " ligne(s) insérée(s)");
            
            if (result > 0) {
                System.out.println("=== Inscription réussie ===");
                return true;
            } else {
                System.out.println("=== Aucune ligne insérée ===");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("=== ERREUR registerUser ===");
            System.out.println("Message : " + e.getMessage());
            System.out.println("Type : " + e.getClass().getName());
            e.printStackTrace();
            return false;
        }
    }
}