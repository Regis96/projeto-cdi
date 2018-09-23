package com.stefanini.pokemon.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

import com.stefanini.pokemon.entities.EntityBase;

public class GenericDAO<I extends Serializable, T extends EntityBase> {

	   @Inject
	   protected EntityManager entityManager;

	   private Class<T> persistedClass;

	   protected GenericDAO() {
	   }

	   protected GenericDAO(Class<T> persistedClass) {
	       this();
	       this.persistedClass = persistedClass;
	   }

	   public T salvar(@Valid T entity) {
	       EntityTransaction t = entityManager.getTransaction();
	       t.begin();
	       entityManager.persist(entity);
	       entityManager.flush();
	       t.commit();
	       return entity;
	   }

	   public T atualizar(@Valid T entity) {
	       EntityTransaction t = entityManager.getTransaction();
	       t.begin();
	       entityManager.merge(entity);
	       entityManager.flush();
	       t.commit();
	       return entity;
	   }

	   public void remover(I id) {
	       T entity = encontrar(id);
	       EntityTransaction tx = entityManager.getTransaction();
	       tx.begin();
	       T mergedEntity = entityManager.merge(entity);
	       entityManager.remove(mergedEntity);
	       entityManager.flush();
	       tx.commit();
	   }

	   public List<T> getList() {
	       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       return entityManager.createQuery(query).getResultList();
	   }

	   public T encontrar(I id) {
	       return entityManager.find(persistedClass, id);
	   }
	}