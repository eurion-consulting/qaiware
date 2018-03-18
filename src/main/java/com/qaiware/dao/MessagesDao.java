package com.qaiware.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qaiware.model.Message;

@Repository
public class MessagesDao {

	@PersistenceContext
	private EntityManager entityManager;
	 
	public void create(Message message) {
		entityManager.persist(message);
	}

	public void update(Message message) {
		entityManager.merge(message);
	}
	 
	public Message getMessageById(long id) {
		return entityManager.find(Message.class, id);
	}
	
	public void delete(long id) {
		Message message = getMessageById(id);
		if (message != null) {
			entityManager.remove(message);
		}
	}
	
	
}
