package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.metier.Categorie;
import modele.metier.Produit;

public class AjoutProduitController implements IAjoutModif<Produit> {

	private DAOFactory dao;

	private MainController main;

	private enumAction action;

	private Produit prod;

	public void defineTF(String nom, String desc, Float tarif, int idcateg) throws Exception {

		idTextNom.setText(nom);

		idTextDesc.setText(desc);

		idTextTarif.setText(String.valueOf(tarif));

		for (Categorie categ : dao.getCategorieDAO().findAll()) {
			if (categ.getIdcategorie() == idcateg) {
				idChoixCateg.setValue(categ);
			}
		}

	}

	@FXML
	private GridPane idGrid1;

	@FXML
	private Label idLabelNom;

	@FXML
	private Label idLabelDesc;

	@FXML
	private Label idLabelTarif;

	@FXML
	private Label idLabelCateg;

	@FXML
	private Label idLabelEuro;

	@FXML
	private TextField idTextNom;

	@FXML
	private TextField idTextTarif;

	@FXML
	private TextArea idTextDesc;

	@FXML
	private ChoiceBox<Categorie> idChoixCateg;

	@FXML
	private Button idBoutonValider;

	@FXML
	private Label idLabelAffi;

	public void init() throws Exception {

		ObservableList<Categorie> list = FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		this.idChoixCateg.setItems(list);
	}

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutProduitController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;
			param.init();

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'un produit");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionU,
			Produit prodU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutProduitController param = fxmlLoader.getController();
			param.action = actionU;
			param.dao = daoF;
			param.main = mainC;
			param.prod = prodU;
			param.init();

			String nom = prodU.getNom();
			String desc = prodU.getDescription();
			Float tarif = prodU.getTarif();
			int idcateg = prodU.getIdcategorie();

			param.defineTF(nom, desc, tarif, idcateg);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification d'une Categorie");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void validation(ActionEvent event) throws Exception {
		if (action == enumAction.Create) {
			try {
				String nom = this.idTextNom.getText().trim();
				String desc = this.idTextDesc.getText().trim();
				Float tarif = Float.parseFloat(this.idTextTarif.getText().trim());
				String visuel = this.idChoixCateg.getValue().getVisuel();
				int idcateg = this.idChoixCateg.getValue().getIdcategorie();

				Produit prodA = new Produit();
				prodA.setNom(nom);
				prodA.setDescription(desc);
				prodA.setTarif(tarif);
				prodA.setVisuel(visuel);
				prodA.setIdcategorie(idcateg);

				dao.getProduitDAO().create(prodA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'un Produit");
				alert.setHeaderText("Produit creer : " + nom + " " + tarif + " " + idcateg);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}
		} else {
			try {
				String nom = this.idTextNom.getText().trim();
				String desc = this.idTextDesc.getText().trim();
				Float tarif = Float.parseFloat(this.idTextTarif.getText().trim());
				String visuel = this.idChoixCateg.getValue().getVisuel();
				int idcateg = this.idChoixCateg.getValue().getIdcategorie();

				prod.setNom(nom);
				prod.setDescription(desc);
				prod.setTarif(tarif);
				prod.setVisuel(visuel);
				prod.setIdcategorie(idcateg);

				dao.getProduitDAO().update(prod);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Categorie modifier : " + nom + " " + tarif + " " + idcateg);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		}

	}

}
