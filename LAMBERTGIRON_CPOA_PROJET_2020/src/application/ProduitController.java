package application;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

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

public class ProduitController implements IAjoutModifVisu<Produit> {

	private DAOFactory dao;

	private MainController main;

	private EnumAction action;

	private Produit prod;

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
	private TextField idTextVisuel;

	@FXML
	private TextArea idTextDesc;

	@FXML
	private ChoiceBox<Categorie> idChoixCateg;

	@FXML
	private Button idBoutonValider;

	@FXML
	private Label idLabelAffi;

	public void defineTF(String nom, String desc, Float tarif, int idcateg, String visu) throws Exception {

		idTextNom.setText(nom);

		idTextDesc.setText(desc);

		idTextTarif.setText(String.valueOf(tarif));

		idTextVisuel.setText(visu);

		for (Categorie categ : dao.getCategorieDAO().findAll()) {
			if (categ.getIdcategorie() == idcateg) {
				idChoixCateg.setValue(categ);
			}
		}

	}

	public void init() throws Exception {

		ObservableList<Categorie> list = FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		this.idChoixCateg.setItems(list);
	}

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ProduitController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;
			param.init();

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'un produit");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionU,
			Produit prodU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ProduitController param = fxmlLoader.getController();
			param.action = actionU;
			param.dao = daoF;
			param.main = mainC;
			param.prod = prodU;
			param.init();

			String nom = prodU.getNom();
			String desc = prodU.getDescription();
			Float tarif = prodU.getTarif();
			int idcateg = prodU.getIdcategorie();
			String visu = prodU.getVisuel();

			param.defineTF(nom, desc, tarif, idcateg, visu);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification d'un produit");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifDoublon(Produit produit) throws Exception {
		for (Produit prod : dao.getProduitDAO().findAll()) {
			produit.setIdproduit(prod.getIdproduit());
			if (produit.getNom().equals(prod.getNom()) && produit.getTarif() == (prod.getTarif())
					&& produit.getIdcategorie() == (prod.getIdcategorie())) {
				throw new IllegalAnnotationException("Produit deja existant !");
			}
		}
	}

	@Override
	public void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction actionV, Produit prodV) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ProduitController param = fxmlLoader.getController();
			param.action = actionV;
			param.dao = daoF;
			param.main = mainC;
			param.init();

			String nom = prodV.getNom();
			String desc = prodV.getDescription();
			Float tarif = prodV.getTarif();
			int idcateg = prodV.getIdcategorie();
			String visu = prodV.getVisuel();

			param.defineTF(nom, desc, tarif, idcateg, visu);
			param.idChoixCateg.setDisable(true);
			param.idTextNom.setDisable(true);
			param.idTextTarif.setDisable(true);
			param.idTextDesc.setDisable(true);
			param.idTextVisuel.setDisable(true);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation d'un produit");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void validation(ActionEvent event) throws Exception {
		if (action == EnumAction.Create) {
			try {
				String nom = this.idTextNom.getText().trim();
				Float tarif = Float.parseFloat(this.idTextTarif.getText().trim());

				Produit prodA = new Produit();
				prodA.setNom(nom);
				prodA.setDescription(this.idTextDesc.getText().trim());
				prodA.setTarif(tarif);
				prodA.setVisuel(this.idTextVisuel.getText().trim().toLowerCase());
				prodA.setIdcategorie(this.idChoixCateg.getValue().getIdcategorie());

				verifDoublon(prodA);
				dao.getProduitDAO().create(prodA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'un Produit");
				alert.setHeaderText("Produit creer : " + this.idChoixCateg.getValue().getTitre() + " nomme : " + nom
						+ " coutant : " + tarif + " .€");
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			} catch (IllegalAnnotationException e1) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Ce produit existe deja !");
			}
		} else if (action == EnumAction.Update) {
			try {
				String nom = this.idTextNom.getText().trim();
				Float tarif = Float.parseFloat(this.idTextTarif.getText().trim());

				prod.setNom(nom);
				prod.setDescription(this.idTextDesc.getText().trim());
				prod.setTarif(tarif);
				prod.setVisuel(this.idTextVisuel.getText().trim().toLowerCase());
				prod.setIdcategorie(this.idChoixCateg.getValue().getIdcategorie());

				dao.getProduitDAO().update(prod);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'un produit");
				alert.setHeaderText("Produit modifier : " + nom + " coutant : " + tarif + " .€");
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		} else {
			Stage stage = (Stage) idBoutonValider.getScene().getWindow();
			stage.close();
		}

	}

}
