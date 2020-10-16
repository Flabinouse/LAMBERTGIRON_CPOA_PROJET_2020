package modele.dao;

import java.util.ArrayList;

public interface IDAO<T> {

	public abstract T getById(int id) throws Exception;

	public abstract boolean create(T object) throws Exception;

	public abstract boolean update(T object) throws Exception;

	public abstract boolean delete(T object) throws Exception;

	public abstract ArrayList<T> findAll() throws Exception;

}
