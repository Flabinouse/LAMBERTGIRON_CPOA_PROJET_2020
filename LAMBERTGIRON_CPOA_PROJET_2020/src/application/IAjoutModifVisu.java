package application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;

public interface IAjoutModifVisu<T> {

	public abstract void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction action);

	public abstract void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction action, T objet);

	public abstract void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction action, T objet);

}
