package Main;

import java.util.Scanner;

import modele.dao.factory.DAOFactory;
import modele.metier.Client;

public class MenuClient {

	public static void menu(DAOFactory daos) throws Exception {

		System.out.println("| MENU CLIENT | \n");

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Saisir le numero de l'action a realiser : \n 1.Inserer un client \n 2.Supprimer un client \n 3.Modifier un client \n 4.Afficher les differents clients \n");
		String lecture = sc.nextLine();
		switch (lecture) {

		case "1":
			System.out.println("| INSERTION | \n");
			System.out.println("Saisir les valeurs suivantes :");
			System.out.println("Nom :");
			String nomi = sc.nextLine();
			System.out.println("Prenom :");
			String prenomi = sc.nextLine();
			System.out.println("Identifiant :");
			String identifianti = sc.nextLine();
			System.out.println("Mot de passe :");
			String mdpi = sc.nextLine();
			System.out.println("Numero d'adresse :");
			String numeroi = sc.nextLine();
			System.out.println("Nom de la rue :");
			String ruei = sc.nextLine();
			System.out.println("Code postal :");
			String postali = sc.nextLine();
			System.out.println("Nom de la ville :");
			String villei = sc.nextLine();
			System.out.println("Nom du pays :");
			String paysi = sc.nextLine();
			Client cli = new Client(1, nomi, prenomi, identifianti, mdpi, numeroi, ruei, postali, villei, paysi);
			daos.getClientDAO().create(cli);
			System.out.println("Insertion reussie !");

			break;

		case "2":
			System.out.println("| SUPPRESSION | \n");
			System.out.println("Saisir l'id du client a supprimer :");
			int ids = sc.nextInt();
			Client recup = daos.getClientDAO().getById(ids);
			daos.getClientDAO().delete(recup);
			System.out.println("Suppression reussie !");
			break;

		case "3":
			System.out.println("| MODIFICATION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID du client a modifier:");
			int idm = sc.nextInt();
			sc.nextLine();
			System.out.println("Nouveau Nom :");
			String nomm = sc.nextLine();
			System.out.println("Nouveau Prenom :");
			String prenomm = sc.nextLine();
			System.out.println("Nouveau identifiant :");
			String identifiantm = sc.nextLine();
			System.out.println("Nouveau mot de passe :");
			String mdpm = sc.nextLine();
			System.out.println("Nouveau numero d'adresse :");
			String numerom = sc.nextLine();
			System.out.println("Nouveau nom de rue :");
			String ruem = sc.nextLine();
			System.out.println("Nouveau code postal :");
			String postalm = sc.nextLine();
			System.out.println("Nouveau nom de ville :");
			String villem = sc.nextLine();
			System.out.println("Nouveau nom de pays :");
			String paysm = sc.nextLine();
			Client cli2 = new Client(idm, nomm, prenomm, identifiantm, mdpm, numerom, ruem, postalm, villem, paysm);
			daos.getClientDAO().update(cli2);
			System.out.println("Modification reussie !");
			break;

		case "4":
			System.out.println("| AFFICHAGE | \n");
			System.out.println(daos.getClientDAO().findAll());
			break;

		default:
			System.out.println("Erreur de saisie, Veuillez reessayer !");
			break;
		}

	}
}
