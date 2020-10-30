package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;

public interface IAjoutModif<T> {

	public abstract void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainApp,
			enumAction action);

	public abstract void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainApp,
			enumAction action, T objet);

}
