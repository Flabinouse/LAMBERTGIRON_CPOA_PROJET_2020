Projet Java 2020 - Lambert Giron

Ce qui fonctionne :
- Ajout, modification, suppression et visualisation fonctionne pour Catégorie, Client, Produit et Commande (MySQL)
- Ajout, modification, suppression et visualisation fonctionne pour Catégorie, Client et Produit (ListeMémoire)
- Ajout, suppression et visualisation fonctionne pour Commande (ListeMémoire)
- Les recherches dans les tables (MySQL et ListeMémoire)
  - Produit : recherche par categorie, nom de produit et tarif(tous les produits inférieurs au tarif rentré)
  - Client : trié par ordre alphabétique, recherche par nom et prenom
  - Commande : recherche filtré par client
- On peut accéder aux commandes d'un client en cliquant dessus
- Présence d'un bouton pour changer de mode de persistance
- Présence d'un bouton pour effacer les recherches

Ce qui ne fonctionne pas :
- Nombre total de produits commandés
- Modification ne fonctionne pas pour Commande (ListeMémoire)
- On ne peut pas faire les recherches des produits dans les Commandes

Répartition des tâches :
- Flavien :
  - Creation fenetre + controller pour Produit
  - Creation fenetre + controller pour Commande
  - Creation fenetre + controller pour Main
- Maxence :
  - Creation fenetre + controller pour Categorie
  - Creation fenetre + controller pour Client
  
Estimation du pourcentage de l'investissement de chacun dans le projet : 
- Flavien : 60%
- Maxence : 40%
