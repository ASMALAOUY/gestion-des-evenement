<p align="center" style="font-size:30px; font-weight:bold;">
 Gestion des Événements — Application Java Swing
</p>

<p align="center">
Une application complète pour gérer les événements, les participants et les inscriptions.<br>
Développée en Java • Base de données MySQL • Graphiques avec JFreeChart
</p>

---

##  Description

Le projet **Gestion Événementiel** est une application développée en **Java (Swing)** permettant de gérer de manière efficace :

- Les **événements**
- Les **participants**
- Les **inscriptions**
- Le **taux de remplissage**
- Les **statistiques graphiques**

Cette solution simple, intuitive et professionnelle facilite l’organisation d’événements et leur suivi en temps réel.



##  Fonctionnalités principales

### 1 Ajouter un événement
- Titre  
- Lieu  
- Date  
- Capacité maximale  

### 2 Ajouter un participant
- Nom  
- Email  
- Organisation  

### 3 Gérer les inscriptions
- Choisir l’événement  
- Choisir le participant  
- Date d’inscription générée automatiquement  

### 4 Filtrage & recherche
- Par **lieu**
- Par **date**

### 5 Calcul automatique du taux de remplissage
Formule :  
\[
\text{Taux} = \frac{\text{Nombre d'inscrits}}{\text{Capacité}} \times 100
\]

### 6 Statistiques & Graphiques
- Diagramme en barres : **Participants par événement**  
  (via **JFreeChart**)



##  Structure de la base de données (MySQL)

###    MCD**
![image alt](https://github.com/ASMALAOUY/gestion-des-evenement/blob/main/Capture%20d%E2%80%99%C3%A9cran%202025-12-02%20173223.jpg?raw=true)


## Codes SQL
Voici les requêtes SQL pour créer les tables et insérer des données :
### Création des tables
```sql
CREATE DATABASE IF NOT EXISTS evenementiel;
USE evenementiel;

-- Table : Evenement
CREATE TABLE Evenement (
    id_evenement INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    lieu VARCHAR(100) NOT NULL,
    dateEv DATE NOT NULL,
    capacite INT NOT NULL
);

-- Table : Participant
CREATE TABLE Participant (
    id_participant INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    organisation VARCHAR(100)
);

-- Table : Inscription
CREATE TABLE Inscription (
    id_inscription INT AUTO_INCREMENT PRIMARY KEY,
    id_evenement INT NOT NULL,
    id_participant INT NOT NULL,
    dateInscription DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY(id_evenement) REFERENCES Evenement(id_evenement),
    FOREIGN KEY(id_participant) REFERENCES Participant(id_participant)
);
```
## Technologies utilisées
Langage : Java
Framework d'interface graphique : Java Swing
API : JavaMail
Base de données : MySQL
Bibliothèque graphique : JFreeChart
Outils de développement :
IDE : NetBeans
Outil de gestion de base de données : phpMyAdmin
Accès aux données : JDBC
