package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.util.Dao;

public class GenericDao<T> implements Dao<T> {
	
	private final Class<T> classe; 
	protected EntityManager em;
	
	public GenericDao(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}
	
	@Override
	public void adicionar(T entidade) {
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked") @Override
	public List<T> listar() {
		return em.createQuery("From " +
				classe.getSimpleName()).getResultList();
	}
	
	@Override
	public void atualizar(T entidade) {
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}
	
	@Override
	public void remover(T entidade) {
		em.getTransaction().begin();
		em.remove(em.merge(entidade));
		em.getTransaction().commit();
	}
	
	@Override
	public T buscar(Object pk) {
		em.getTransaction().begin();
		T entidade = em.find(classe,pk);
		em.getTransaction().commit();
		return entidade;
	}

	@Override
	public List<T> listar(String jpql, Object... params) {
		try {
			Query query = em.createQuery(jpql);
			for(int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
