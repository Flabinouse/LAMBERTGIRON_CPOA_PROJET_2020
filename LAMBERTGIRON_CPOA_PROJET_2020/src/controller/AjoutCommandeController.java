package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Commande;

public class AjoutCommandeController {

	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML
	private DatePicker idDtpDatecom;

	@FXML
	private TextField idTextIdclient;

	@FXML
	private Button idBoutonCreer;

	@FXML
	private Button idBoutonModif;

	@FXML
	private Label idLabelAffi;

	@FXML
	public void creerCommande(ActionEvent event) throws Exception {

		try {
			LocalDate datecom = idDtpDatecom.getValue();
			int idcli = Integer.parseInt(this.idTextIdclient.getText().trim());
			Commande com = new Commande();
			com.setDatecom(datecom);
			com.setIdcli(idcli);
			dao.getCommandeDAO().create(com);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ajout d'une commande");
			alert.setHeaderText("Commande cree : " + datecom + " " + idcli);
			alert.showAndWait();
			Stage stage = (Stage) idBoutonCreer.getScene().getWindow();
			stage.close();

		} catch (IllegalArgumentException e) {
			this.idLabelAffi.setStyle("-fx-text-fill: red;");
			this.idLabelAffi.setText("Veuillez saisir tous les champs");
		}
	}

	@FXML
	public void modifCommande(ActionEvent event) throws Exception {
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
