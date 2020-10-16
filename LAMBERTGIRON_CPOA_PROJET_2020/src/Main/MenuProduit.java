package Main;

import java.util.Scanner;

import modele.dao.factory.DAOFactory;
import modele.metier.Produit;

public class MenuProduit {

	public static void menu(DAOFactory daos) throws Exception {

		System.out.println("| MENU PRODUIT | \n");

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Saisir le numero de l'action a realiser : \n 1.Inserer un produit \n 2.Supprimer un produit \n 3.Modifier un produit \n 4.Afficher les differents produits \n");
		String lecture = sc.nextLine();
		switch (lecture) {

		case "1":
			System.out.println("| INSERTION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("Nom :");
			String nomi = sc.nextLine();
			System.out.println("Description :");
			String descriptioni = sc.nextLine();
			System.out.println("Tarif :");
			float tarifi = sc.nextFloat();
			sc.nextLine();
			System.out.println("Visuel :");
			String visueli = sc.nextLine();
			System.out.println("ID categorie :");
			int id_categoriei = sc.nextInt();
			Produit prod = new Produit(1, nomi, descriptioni, tarifi, visueli, id_categoriei);
			daos.getProduitDAO().create(prod);
			System.out.println("Insertion reussie !");

			break;

		case "2":
			System.out.println("| SUPPRESSION | \n");
			System.out.println("Veuillez saisir l'id du produit a supprimer :");
			int ids = sc.nextInt();
			Produit recup = daos.getProduitDAO().getById(ids);
			daos.getProduitDAO().delete(recup);
			System.out.println("Suppression reussie !");
			break;

		case "3":
			System.out.println("| MODIFICATION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID du produit a modifier :");
			int idm = sc.nextInt();
			sc.nextLine();
			System.out.println("Nouveau Nom :");
			String nomm = sc.nextLine();
			System.out.println("Nouvelle Description :");
			String descriptionm = sc.nextLine();
			System.out.println("Nouveau Tarif :");
			float tarifm = sc.nextFloat();
			sc.nextLine();
			System.out.println("Nouveau Visuel :");
			String visuelm = sc.nextLine();
			System.out.println("Nouveau ID categorie :");
			int id_categoriem = sc.nextInt();
			Produit prod2 = new Produit(idm, nomm, descriptionm, tarifm, visuelm, id_categoriem);
			daos.getProduitDAO().update(prod2);
			System.out.println("Modification reussie !");
			break;

		case "4":
			System.out.println("| AFFICHAGE | \n");
			System.out.println(daos.getProduitDAO().findAll());
			break;

		default:
			System.out.println("Erreur de saisie, Veuillez reessayer !");
			break;
		}

	}
}
