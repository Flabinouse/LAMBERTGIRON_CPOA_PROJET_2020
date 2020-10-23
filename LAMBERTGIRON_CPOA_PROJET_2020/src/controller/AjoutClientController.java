package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Client;

public class AjoutClientController {

	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML
	private TextField idTextNom;

	@FXML
	private TextField idTextPrenom;

	@FXML
	private TextField idTextIdent;

	@FXML
	private TextField idTextMdp;

	@FXML
	private TextField idTextNum;

	@FXML
	private TextField idTextRue;

	@FXML
	private TextField idTextPostal;

	@FXML
	private TextField idTextVille;

	@FXML
	private TextField idTextPays;

	@FXML
	private Button BoutonCreer;

	@FXML
	private Button BoutonModif;

	@FXML
	private Label idLabelAffi;

	@FXML
	public void creerClient(ActionEvent event) throws Exception {

		try {
			String nom = this.idTextNom.getText().trim();
			String prenom = this.idTextPrenom.getText().trim();
			String ident = this.idTextIdent.getText().trim();
			String mdp = this.idTextMdp.getText().trim();
			String numero = this.idTextNum.getText().trim();
			String rue = this.idTextRue.getText().trim();
			String postal = this.idTextPostal.getText().trim();
			String ville = this.idTextVille.getText().trim();
			String pays = this.idTextPays.getText().trim();
			Client cli = new Client();
			cli.setNom(nom);
			cli.setPrenom(prenom);
			cli.setIdentifiant(ident);
			cli.setMdp(mdp);
			cli.setNumero(numero);
			cli.setRue(rue);
			cli.setPostal(postal);
			cli.setVille(ville);
			cli.setPays(pays);
			dao.getClientDAO().create(cli);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ajout d'un client");
			alert.setHeaderText("Client cree : " + nom + " " + prenom + " " + ident + " " + mdp + " " + numero + " "
					+ rue + " " + postal + " " + ville + " " + pays);
			alert.showAndWait();
			Stage stage = (Stage) BoutonCreer.getScene().getWindow();
			stage.close();

		} catch (IllegalArgumentException e) {
			this.idLabelAffi.setStyle("-fx-text-fill: red;");
			this.idLabelAffi.setText("Veuillez saisir tous les champs");
		}
	}

	@FXML
	public void modifClient(ActionEvent event) throws Exception {
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
