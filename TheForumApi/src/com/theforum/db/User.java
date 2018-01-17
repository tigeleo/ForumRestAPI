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
@Table(name = "forum_users_details")
public class User extends EntityTimestamp {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	 
	
	@Column(name = "name")
	private String name;
	
	 

	@Column(name = "sex")
	private char sex;
	
	@Column(name = "birth")
	private Date birth;
	

	
	public User() {
	
	}
	
	
	
	 
	
	public char getSex() {
		return sex;
	}





	public void setSex(char sex) {
		this.sex = sex;
	}





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}







	public Date getBirth() {
		return birth;
	}





	public void setBirth(Date birth) {
		this.birth = birth;
	}





	@Override
	public String toString() {
	
	    return "{User:{id: " + this.id + ",name:" + this.name + ",birth:" + this.birth + ",sex:"+this.sex +"}";
	
	}
	
 

}
