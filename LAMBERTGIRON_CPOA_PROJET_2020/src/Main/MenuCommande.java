package Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import modele.dao.factory.DAOFactory;
import modele.metier.Commande;

public class MenuCommande {

	public static void menu(DAOFactory daos) throws Exception {

		System.out.println("| MENU COMMANDE | \n");

		Scanner sc = new Scanner(System.in);

		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println(
				"Saisir le numero de l'action a realiser : \n 1.Inserer une commande \n 2.Supprimer une commande \n 3.Modifier une commande \n 4.Afficher les differentes commandes \n");
		String lecture = sc.nextLine();
		switch (lecture) {

		case "1":
			System.out.println("| INSERTION | \n");
			System.out.println("Veuillez saisir dans les �l�ments suivant :");
			System.out.println("Date au format dd/MM/yyyy");
			LocalDate datecomi = LocalDate.parse(sc.nextLine(), formatage);
			System.out.println("ID client :");
			int idclii = sc.nextInt();
			Commande com = new Commande(1, datecomi, idclii);
			daos.getCommandeDAO().create(com);
			System.out.println("Insertion reussie !");

			break;

		case "2":
			System.out.println("| SUPPRESSION | \n");
			System.out.println("Veuillez saisir l'id de la commande a supprimer :");
			int ids = sc.nextInt();
			Commande recup = daos.getCommandeDAO().getById(ids);
			daos.getCommandeDAO().delete(recup);
			System.out.println("Suppression reussie !");
			break;

		case "3":
			System.out.println("| MODIFICATION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID de la commande a modifier :");
			int idm = sc.nextInt();
			sc.nextLine();
			System.out.println("Nouvelle date :");
			LocalDate datecomm = LocalDate.parse(sc.nextLine(), formatage);
			System.out.println("Nouveau ID client :");
			int idclim = sc.nextInt();
			Commande com2 = new Commande(idm, datecomm, idclim);
			daos.getCommandeDAO().update(com2);
			System.out.println("Modification reussie !");
			break;

		case "4":
			System.out.println("| AFFICHAGE | \n");
			System.out.println(daos.getCommandeDAO().findAll());
			break;

		default:
			System.out.println("Erreur de saisie, Veuillez reessayer !");
			break;
		}

	}
}
