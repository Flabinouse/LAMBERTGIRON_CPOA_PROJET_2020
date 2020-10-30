package controller;

import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;

public class AjoutCategorieController extends Application {

	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML
	private TextField idTextTitre;

	@FXML
	private TextField idTextVisu;

	@FXML
	private Button idBoutonCreer;

	@FXML
	private Button idBoutonModif;

	@FXML
	private Label idLabelAffi;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			URL fxmlURL = getClass().getResource("/fxml/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 350, 300);
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

	public void defineCateg(Categorie categ) {

		this.idTextTitre.setText(categ.getTitre());
		this.idTextVisu.setText(categ.getVisuel());
	}

	@FXML
	public void creerCategorie(ActionEvent event) throws Exception {

		try {
			String titre = this.idTextTitre.getText().trim();
			String visuel = this.idTextVisu.getText().trim();
			Categorie categ = new Categorie();
			categ.setTitre(titre);
			categ.setVisuel(visuel);
			dao.getCategorieDAO().create(categ);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ajout d'une categorie");
			alert.setHeaderText("Categorie creer : " + titre + " " + visuel);
			alert.showAndWait();
			Stage stage = (Stage) idBoutonCreer.getScene().getWindow();
			stage.close();

		} catch (IllegalArgumentException e) {
			this.idLabelAffi.setStyle("-fx-text-fill: red;");
			this.idLabelAffi.setText("Veuillez saisir tous les champs");
		}
	}

	@FXML
	public void modifCategorie(ActionEvent event) throws Exception {
		System.out.println("salut ca marche");
	}

}
