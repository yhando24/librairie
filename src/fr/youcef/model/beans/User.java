package fr.youcef.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
//	Beans gerer par hibernate
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=90, nullable=false)
	private String firstname;
	@Column(length=90, nullable=false)
	private String lastname;
	@Column(length=90, nullable=false, unique=true)
	private String pseudo;
	@Column(length=90, nullable=false, unique=true)
	private String email;
	@Column(length=40, nullable=false)
	private String password;
	@Column(length=20, nullable=false)
	private String role;
	
	
	public User() {}
	public User( String firstname, String lastname, String pseudo, String email, String password, String role) {
		
	
		this.firstname = firstname;
		this.lastname = lastname;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User(int id, String firstname, String lastname, String pseudo, String email, String password, String role) {
		this(firstname, lastname, pseudo, email, password, role);
		this.id = id;
	
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
