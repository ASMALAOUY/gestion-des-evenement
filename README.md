

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
Taux de remplissage=(capacite/nombre d’inscrits)​×100

### 6 Statistiques & Graphiques
- Diagramme en barres : **Participants par événement**  
  (via **JFreeChart**)



##  Structure de la base de données (MySQL)

###    MCD**
![image alt](https://raw.githubusercontent.com/ASMALAOUY/gestion-des-evenement/fb1ab33714412b3254835394d81eefb39c5ce47d/image.png)


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
## Technologies et outils utilisés

Le projet est développé avec les technologies et outils suivants :

### Langage de programmation
- **Java** : Langage principal pour la logique applicative et le développement de l'interface.

### Framework et bibliothèques
- **Java Swing** : Pour la création de l'interface graphique (GUI).
- **JFreeChart** : Pour la génération de graphiques et la visualisation de données.
- **JavaMail API** : Pour l'envoi et la gestion des e-mails depuis l'application.

### Base de données
- **MySQL** : Base de données relationnelle pour stocker les données de l'application.
- **JDBC** : Pour la connexion et l'accès aux données depuis Java.
- **phpMyAdmin** : Outil de gestion de base de données MySQL.

### Environnement et outils de développement
- **NetBeans IDE** : Environnement de développement intégré pour le codage, le débogage et la compilation.

## Architecture du projet :

![image alt](https://raw.githubusercontent.com/ASMALAOUY/gestion-des-evenement/77397f2c32fac67f58d4efa30d24958bcb9a4450/image.png)

## demo



https://github.com/user-attachments/assets/fab21151-1215-40d2-b2f2-80d2faf63aa1







## Auteur
Nom : ASMA LAOUY

Cours : Programmation java

Date : Décembre 2025

Encadré par : Pr. Mohamed LACHGAR
