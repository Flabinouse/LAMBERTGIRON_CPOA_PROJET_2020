package Main;

import java.util.Scanner;

import modele.dao.factory.DAOFactory;
import modele.metier.LigneCommande;

public class MenuLigneCommande {

	public static void menu(DAOFactory daos) throws Exception {

		System.out.println("| MENU LIGNECOMMANDE | \n");

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Saisir le numero de l'action a realiser : \n 1.Inserer une ligne de commande \n 2.Supprimer une ligne de commande \n 3.Modifier une ligne de commande \n 4.Afficher les differentes lignes de commandes \n");
		String lecture = sc.nextLine();
		switch (lecture) {

		case "1":
			System.out.println("| INSERTION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID commande :");
			int idcomi = sc.nextInt();
			System.out.println("ID produit :");
			int idprodi = sc.nextInt();
			System.out.println("Quantite :");
			int quantitei = sc.nextInt();
			System.out.println("Tarif unitaire :");
			double tarifuniti = sc.nextDouble();
			LigneCommande lignecom = new LigneCommande(idcomi, idprodi, quantitei, tarifuniti);
			daos.getLigneCommandeDAO().create(lignecom);
			System.out.println("Insertion reussie !");
			break;

		case "2":
			System.out.println("| SUPPRESSION | \n");
			System.out.println("Veuillez saisir les id suivant pour la suppression :");
			System.out.println("ID commande :");
			int idscom = sc.nextInt();
			System.out.println("ID produit :");
			int idsprod = sc.nextInt();
			LigneCommande recup = daos.getLigneCommandeDAO().getById(idscom, idsprod);
			daos.getLigneCommandeDAO().delete(recup);
			System.out.println("Suppression reussie !");
			break;

		case "3":
			System.out.println("| MODIFICATION | \n");
			System.out.println("Veuillez saisir les elements suivant :");
			System.out.println("ID commande :");
			int idcomm = sc.nextInt();
			System.out.println("ID produit :");
			int idprod = sc.nextInt();
			System.out.println("Nouvelle Quantite :");
			int quantitem = sc.nextInt();
			System.out.println("Nouveau tarif unitaire :");
			double tarifunitm = sc.nextDouble();
			LigneCommande lignecom2 = new LigneCommande(idcomm, idprod, quantitem, tarifunitm);
			daos.getLigneCommandeDAO().update(lignecom2);
			System.out.println("Modification reussie !");
			break;

		case "4":
			System.out.println("| AFFICHAGE | \n");
			System.out.println(daos.getLigneCommandeDAO().findAll());
			break;

		default:
			System.out.println("Erreur de saisie, Veuillez reessayer !");
			break;
		}

	}
}
