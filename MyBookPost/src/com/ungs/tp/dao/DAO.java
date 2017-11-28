package com.ungs.tp.dao;

import java.util.List;

public interface DAO<T> {
	
	public void crear(T t);
	public void eliminar(T t);	
	public T porId(long id);	
	public List<T> todos();
	public void actualizar(T t);

}

