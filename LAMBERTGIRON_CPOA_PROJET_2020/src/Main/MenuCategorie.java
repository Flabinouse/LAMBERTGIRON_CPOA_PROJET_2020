package Main;

import java.util.Scanner;

import modele.dao.factory.DAOFactory;
import modele.metier.Categorie;

public class MenuCategorie {

	public static void menu(DAOFactory daos) throws Exception {

		System.out.println("| MENU CATEGORIE | \n");

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Saisir le numero de l'action a realiser : \n 1.Inserer une categorie \n 2.Supprimer une categorie \n 3.Modifier une categorie \n 4.Afficher les differentes categories \n");
		String lecture = sc.nextLine();
		switch (lecture) {

		case "1":
			System.out.println("| INSERTION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("Titre :");
			String titrei = sc.nextLine().trim();
			System.out.println("Visuel :");
			String visueli = sc.nextLine().trim();
			Categorie categ = new Categorie(1, titrei, visueli);
			daos.getCategorieDAO().create(categ);
			System.out.println("Insertion reussie !");

			break;

		case "2":
			System.out.println("| SUPPRESSION | \n");
			System.out.println("Veuillez saisir l'id de la categorie a supprimer :");
			int ids = sc.nextInt();
			Categorie recup = daos.getCategorieDAO().getById(ids);
			daos.getCategorieDAO().delete(recup);
			System.out.println("Suppression reussie !");
			break;

		case "3":
			System.out.println("| MODIFICATION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID de la categorie a modifier:");
			int idm = sc.nextInt();
			sc.nextLine();
			System.out.println("Nouveau Titre :");
			String titrem = sc.nextLine().trim();
			System.out.println("Nouveau Visuel :");
			String visuelm = sc.nextLine().trim();
			Categorie categ2 = new Categorie(idm, titrem, visuelm);
			daos.getCategorieDAO().update(categ2);
			System.out.println("Modification reussie !");
			break;

		case "4":
			System.out.println("| AFFICHAGE | \n");
			System.out.println(daos.getCategorieDAO().findAll());
			break;

		default:
			System.out.println("Erreur de saisie, Veuillez reessayer !");
			break;
		}

	}

}
