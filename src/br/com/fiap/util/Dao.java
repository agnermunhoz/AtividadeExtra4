package br.com.fiap.util;

import java.util.List;

public interface Dao<T> {

	public void adicionar(T entidade);
	public List<T> listar();
	public void atualizar(T entidade);
	public void remover(T entidade);
	public T buscar(Object pk);
	public List<T> listar(String jpql, Object... params);
	
}
