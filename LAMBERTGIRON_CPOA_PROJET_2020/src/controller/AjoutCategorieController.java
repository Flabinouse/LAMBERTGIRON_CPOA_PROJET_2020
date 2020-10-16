package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;

public class AjoutCategorieController {

	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML
	private GridPane idGrid1;

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
			alert.setHeaderText("Categorie créer : " + titre + " " + visuel);
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
		/*
		 * try { String nom = this.idTextNom.getText().trim(); String description =
		 * this.idTextDesc.getText().trim(); float tarif =
		 * Float.parseFloat(this.idTextTarif.getText().trim()); Produit prod = new
		 * Produit(); prod.setId_produit(0);//a modif prod.setNom(nom);
		 * prod.setTarif(tarif); prod.setDescription(description);
		 * prod.setVisuel(this.idChoixCateg.getValue().getVisuel());
		 * prod.setId_categorie(this.idChoixCateg.getValue().getId_categorie());
		 * dao.getProduitDAO().update(prod); Alert alert=new
		 * Alert(Alert.AlertType.INFORMATION); alert.setTitle("Ajout d'un produit");
		 * alert.setHeaderText("Produit créer : " + nom + " " + description + "(" +
		 * this.idChoixCateg.getValue().getTitre() + "), " + tarif);
		 * alert.showAndWait(); Stage stage = (Stage)
		 * idBoutonCreer.getScene().getWindow(); stage.close();
		 * 
		 * } catch (IllegalArgumentException e) {
		 * this.idLabelRes.setStyle("-fx-text-fill: red;");
		 * this.idLabelRes.setText("Veuillez saisir tous les champs"); }
		 */
	}

}
