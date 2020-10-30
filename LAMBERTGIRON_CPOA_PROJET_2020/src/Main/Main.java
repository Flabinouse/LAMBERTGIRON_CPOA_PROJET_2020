package Main;

import java.net.URL;
//import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import modele.dao.factory.DAOFactory;
//import modele.dao.factory.Persistance;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			URL fxmlURL = getClass().getResource("/fxml/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 755.0, 475.0);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Application de gestion des pulls de noel");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * System.out.
	 * println("||| BIENVENUE DANS L'APPLICATION DE GESTION DES PULLS DE NOEL ||| \n"
	 * );
	 * 
	 * String reponse = "N"; Scanner sc = new Scanner(System.in); DAOFactory daos =
	 * null; boolean selectdao = false;
	 * 
	 * while (selectdao != true) {
	 * 
	 * System.out.println("Voulez-vous utiliser : 1.MySQL 2.ListeMemoire ? \n");
	 * String select = sc.nextLine(); switch (select) { case "1": daos =
	 * DAOFactory.getDAOFactory(Persistance.MYSQL); selectdao = true; break;
	 * 
	 * case "2": daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
	 * selectdao = true; break;
	 * 
	 * default: System.out.println("Erreur de saisie, Veuillez reessayer !"); break;
	 * } }
	 * 
	 * do {
	 * 
	 * System.out.println(
	 * "Saisir le numero du menu voulu : \n 1.Categorie \n 2.Client \n 3.Produit \n 4.Commande \n 5.Ligne de Commande \n"
	 * ); String lecture = sc.nextLine();
	 * 
	 * switch (lecture) { case "1": try { MenuCategorie.menu(daos); } catch
	 * (Exception e) { e.printStackTrace(); } break;
	 * 
	 * case "2": try { MenuClient.menu(daos); } catch (Exception e) {
	 * e.printStackTrace(); } break;
	 * 
	 * case "3": try { MenuProduit.menu(daos); } catch (Exception e) {
	 * e.printStackTrace(); } break;
	 * 
	 * case "4": try { MenuCommande.menu(daos); } catch (Exception e) {
	 * e.printStackTrace(); } break;
	 * 
	 * case "5": try { MenuLigneCommande.menu(daos); } catch (Exception e) {
	 * e.printStackTrace(); } break;
	 * 
	 * default: System.out.println("Erreur de saisie, Veuillez reessayer !"); break;
	 * }
	 * 
	 * System.out.println("Voulez-vous continuer O/N ? \n"); reponse =
	 * sc.nextLine();
	 * 
	 * } while (reponse.equals("O") || reponse.equals("o"));
	 */
}