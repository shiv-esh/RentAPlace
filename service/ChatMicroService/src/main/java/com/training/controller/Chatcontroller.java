package com.training.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Chat;
import com.training.service.ChatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/chat")
public class Chatcontroller {
	@Autowired
	ChatService chatService;
	
	@PostMapping("/send")
	public Chat Sendmsg(@RequestBody Chat chat) {
		return chatService.send(chat);
	}
	@GetMapping("/get/{puid}")
	public List<Chat> Getmsg(@PathVariable("puid") String puid){
		String[] splitted=puid.split(",");
		int pid=Integer.parseInt(splitted[0]);
		int uid=Integer.parseInt(splitted[1]);
		return chatService.get(pid, uid);
		
	}
	@GetMapping("/getowner/{pid}")
	public List<Chat> GetOwnermsg(@PathVariable("pid") int oid){
		return chatService.getowner(oid);
	}

}
