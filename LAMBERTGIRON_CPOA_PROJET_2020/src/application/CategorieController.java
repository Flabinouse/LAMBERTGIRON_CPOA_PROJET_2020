package application;

import java.io.IOException;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.metier.Categorie;

public class CategorieController implements IAjoutModifVisu<Categorie> {

	private DAOFactory dao;

	private MainController main;

	private EnumAction action;

	private Categorie categ;

	@FXML
	private TextField idTextTitre;

	@FXML
	private TextField idTextVisu;

	@FXML
	private Button idValid;

	@FXML
	private Label idLabelAffi;

	public void defineTF(String titre, String visuel) {

		idTextTitre.setText(titre);

		idTextVisu.setText(visuel);
	}

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			CategorieController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'une Categorie");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionU,
			Categorie categU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			CategorieController param = fxmlLoader.getController();
			param.action = actionU;
			param.dao = daoF;
			param.main = mainC;
			param.categ = categU;

			String titre = categU.getTitre();
			String visuel = categU.getVisuel();

			param.defineTF(titre, visuel);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification d'une Categorie");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifDoublon(Categorie categ) throws Exception {
		for (Categorie cat : dao.getCategorieDAO().findAll()) {
			categ.setIdcategorie(cat.getIdcategorie());
			if (categ.getTitre().equals(cat.getTitre()) && categ.getVisuel().equals(cat.getVisuel())) {
				throw new IllegalAnnotationException("Categorie deja existante !");
			}
		}
	}

	public void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction actionV, Categorie categV) {

		try {

			Parent root = (Parent) fxmlLoader.load();

			CategorieController param = fxmlLoader.getController();
			param.action = actionV;
			param.dao = daoF;
			param.main = mainC;

			String titre = categV.getTitre();
			String visuel = categV.getVisuel();

			param.defineTF(titre, visuel);
			param.idTextTitre.setDisable(true);
			param.idTextVisu.setDisable(true);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation d'une Categorie");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void validation(ActionEvent event) throws Exception {
		if (action == EnumAction.Create) {
			try {
				String titre = this.idTextTitre.getText().trim().toLowerCase();
				Categorie categA = new Categorie();
				categA.setTitre(titre);
				categA.setVisuel(this.idTextVisu.getText().trim().toLowerCase());

				verifDoublon(categA);
				dao.getCategorieDAO().create(categA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Categorie creer : " + titre);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idValid.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			} catch (IllegalAnnotationException e1) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Cette categorie existe deja !");
			}
		} else if (action == EnumAction.Update) {
			try {
				String titre = this.idTextTitre.getText().trim().toLowerCase();
				categ.setTitre(titre);
				categ.setVisuel(this.idTextVisu.getText().trim().toLowerCase());

				dao.getCategorieDAO().update(categ);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Categorie modifier : " + titre);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idValid.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		} else {
			Stage stage = (Stage) idValid.getScene().getWindow();
			stage.close();
		}

	}

}
