package controller;

//import java.util.concurrent.TimeUnit;
//TimeUnit.SECONDS.sleep(5);

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;
import modele.metier.Produit;

public class AjoutProduitController {

	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML private GridPane idGrid1;

	@FXML private Label idLabelNom;

	@FXML private Label idLabelDesc;

	@FXML private Label idLabelTarif;

	@FXML private Label idLabelCateg;

	@FXML private Label idLabelEuro;

	@FXML private Label idLabelRes;

	@FXML private TextField idTextNom;

	@FXML private TextField idTextTarif;

	@FXML private TextArea idTextDesc;

	@FXML private ChoiceBox<Categorie> idChoixCateg;

	@FXML private Button idBoutonCreer;

	@FXML
	public void initialize() throws Exception {

		ObservableList<Categorie> list = FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		this.idChoixCateg.setItems(list);
	}

	@FXML
	public void creerModele(ActionEvent event) throws Exception {

		try {
			String nom = this.idTextNom.getText().trim();
			String description = this.idTextDesc.getText().trim();
			float tarif = Float.parseFloat(this.idTextTarif.getText().trim());
			Produit prod = new Produit();
			prod.setNom(nom);
			prod.setTarif(tarif);
			prod.setDescription(description);
			prod.setVisuel(this.idChoixCateg.getValue().getVisuel());
			prod.setIdcategorie(this.idChoixCateg.getValue().getIdcategorie());
			dao.getProduitDAO().create(prod);
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ajout d'un produit");
			alert.setHeaderText("Produit créer : " + nom + " " + description + "(" + this.idChoixCateg.getValue().getTitre() + "), " + tarif);
			alert.showAndWait();
			Stage stage = (Stage) idBoutonCreer.getScene().getWindow();
			stage.close();

		} catch (IllegalArgumentException e) {
			this.idLabelRes.setStyle("-fx-text-fill: red;");
			this.idLabelRes.setText("Veuillez saisir tous les champs");
		}
	}
	
	@FXML
	public void modifModele(ActionEvent event) throws Exception {
	/*	try {
			String nom = this.idTextNom.getText().trim();
			String description = this.idTextDesc.getText().trim();
			float tarif = Float.parseFloat(this.idTextTarif.getText().trim());
			Produit prod = new Produit();
			prod.setId_produit(0);//a modif 
			prod.setNom(nom);
			prod.setTarif(tarif);
			prod.setDescription(description);
			prod.setVisuel(this.idChoixCateg.getValue().getVisuel());
			prod.setId_categorie(this.idChoixCateg.getValue().getId_categorie());
			dao.getProduitDAO().update(prod);
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ajout d'un produit");
			alert.setHeaderText("Produit créer : " + nom + " " + description + "(" + this.idChoixCateg.getValue().getTitre() + "), " + tarif);
			alert.showAndWait();
			Stage stage = (Stage) idBoutonCreer.getScene().getWindow();
			stage.close();

		} catch (IllegalArgumentException e) {
			this.idLabelRes.setStyle("-fx-text-fill: red;");
			this.idLabelRes.setText("Veuillez saisir tous les champs");
		}*/
	}

}
