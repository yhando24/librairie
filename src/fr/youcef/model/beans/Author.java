package fr.youcef.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import fr.youcef.model.utils.Country;
@Entity
public class Author implements Serializable {
	/**
	 * 
	 */
	
//	Beans gerer par hibernate
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=90, nullable=true)
	private String firstname;
	@Column(length=90, nullable=true)
	private String lastname;
	@Enumerated(EnumType.STRING)
	private Country nativeCountry;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST, mappedBy="authors")
	
	private List <Book> books = new ArrayList<Book>();	
	
	public Author() {};
	public Author(String firstname, String lastname, Country nativeCountry ) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.nativeCountry = nativeCountry;
	}
	public Author(int id, String firstname, String lastname, Country nativeCountry) {
	  this(firstname, lastname, nativeCountry); // rappel le constructeur plus haut
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
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	// permt de rajouter un auteur a un set auteur
		public void addBook(Book book) {
			this.books.add(book);
			
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
	public Country getNativeCountry() {
		return nativeCountry;
	}
	public void setNativeCountry(Country nativeCountry) {
		this.nativeCountry = nativeCountry;
	}
	

}
