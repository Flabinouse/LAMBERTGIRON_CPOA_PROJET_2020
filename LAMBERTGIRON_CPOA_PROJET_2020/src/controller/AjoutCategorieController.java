package controller;

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

public class AjoutCategorieController implements IAjoutModif<Categorie> {

	private DAOFactory dao;

	private MainController main;

	private enumAction action;

	private Categorie categ;

	public void defineTF(String titre, String visuel) {

		idTextTitre.setText(titre);

		idTextVisu.setText(visuel);
	}

	@FXML
	private TextField idTextTitre;

	@FXML
	private TextField idTextVisu;

	@FXML
	private Button idValid;

	@FXML
	private Label idLabelAffi;

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutCategorieController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'une Categorie");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionU,
			Categorie categU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutCategorieController param = fxmlLoader.getController();
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
				String titre = this.idTextTitre.getText().trim();
				String visuel = this.idTextVisu.getText().trim();
				Categorie categA = new Categorie();
				categA.setTitre(this.idTextTitre.getText().trim());
				categA.setVisuel(this.idTextVisu.getText().trim());

				dao.getCategorieDAO().create(categA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Categorie creer : " + titre + " " + visuel);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idValid.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}
		} else {
			try {
				String titre = this.idTextTitre.getText().trim();
				String visuel = this.idTextVisu.getText().trim();
				categ.setTitre(this.idTextTitre.getText().trim());
				categ.setVisuel(this.idTextVisu.getText().trim());

				dao.getCategorieDAO().update(categ);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Categorie modifier : " + titre + " " + visuel);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idValid.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		}

	}

}
