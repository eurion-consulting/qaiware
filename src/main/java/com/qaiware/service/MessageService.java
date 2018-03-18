package com.qaiware.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaiware.dao.MessagesDao;
import com.qaiware.model.Message;

@Service
public class MessageService {
	
	@Autowired
	private MessagesDao messagesDao;

	@Transactional
	public void create(Message message) {
		messagesDao.create(message);
	}
	
}
