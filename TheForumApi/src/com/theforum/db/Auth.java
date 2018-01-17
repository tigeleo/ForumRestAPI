package com.theforum.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name = "forum_auth")
public class Auth extends EntityTimestamp{


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	 
	
	@Column(name = "login", unique = true)
	private String login;
	
	@Column(name = "email")
	private String email;
	 
	@Column(name = "password")
	private String password;
	
	
	@Column(name = "rolle")
	@Enumerated(EnumType.STRING)
	private Rolls rolle;
	

	
	
	public Auth() {
	
	}
	
	
	
	 
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Rolls getRolle() {
		return rolle;
	}





	public void setRolle(Rolls rolle) {
		this.rolle = rolle;
	}






	public String getLogin() {
		return login;
	}






	public void setLogin(String login) {
		this.login = login;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}












	@Override
	public String toString() {
	
	    return "{Auth:{id: " + this.id + ",login:" + this.login + ", roll:" + this.rolle + "}";
	
	}
	
 

}
