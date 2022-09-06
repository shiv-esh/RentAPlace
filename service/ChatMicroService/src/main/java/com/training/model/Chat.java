package com.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private int pid;
    private String message;
    
    private int uid;
    private String username;
    private int oid;
    private String ownername;
    private int sid;
	

}
