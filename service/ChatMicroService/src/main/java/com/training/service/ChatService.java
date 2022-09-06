package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Chat;
import com.training.repo.ChatRepo;

@Service
public class ChatService {
	@Autowired
	ChatRepo chatRepo;
	
	public Chat send(Chat chat) {
		Chat chat1= new Chat();
		chat1.setMessage(chat.getMessage());
		chat1.setPid(chat.getPid());
		chat1.setUid(chat.getUid());
		chat1.setUsername(chat.getUsername());
		chat1.setOid(chat.getOid());
		chat1.setOwnername(chat.getOwnername());
		chat1.setSid(chat.getSid());;
		chatRepo.save(chat1);
		
		return chat1;
	}
	public List<Chat> get(int pid,int uid) {
		List<Chat> chats=chatRepo.findByPidAndUid(pid,uid);
		
		
		return chats;
	}
	public List<Chat> getowner(int pid) {
		List<Chat> chats=chatRepo.findByPid(pid);
		
		
		return chats;
	}
	
	

}
