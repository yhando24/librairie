package fr.youcef.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.Type;

@Entity
public class Book  implements Serializable{
	
//	Beans gerer par hibernate
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	@Column(length=70, nullable=true)
	private String title;
	
	@Column(columnDefinition="TEXT", nullable=true) // columnDefinition on ecrit ce quon veux en mode sql
	private String overview;
	
	@Column(precision=5, scale=2, nullable=true)
	private float price;
	
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean available=true;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
			name="Books_Authors",
			joinColumns=@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_books")),
			inverseJoinColumns=@JoinColumn(name="author_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_authors")))
	private List <Author> authors = new ArrayList<Author>();
	

	public Book() {};
	
	// constructeur pour envoyer sur la base de donnée
	public Book(String title, String overview, float price, boolean availability, List <Author> authors) {
		this();
		this.title = title;
		this.overview = overview;
		this.price = price;
		this.available = availability;
		this.authors=authors;
	}
	 // constructeur pour recuperer de la base de donnée
	public Book (int id, String title, String overview, float price, boolean availability, List <Author> authors) {
		this(title, overview, price, availability, authors);
		this.id=id;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
// permet de recuper un set d'auteur
	public List<Author> getAuthors() {
		return authors;
	}
// permet de mettre un set d'auteur dans un set dauteur
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
// permt de rajouter un auteur a un set auteur
	public void addAuthor(Author author) {
		this.authors.add(author);
		author.addBook(this);
	}
//	public void addUser(User user) {
//	this.users.add(user);
//	// on ajoute dans la liste d'user de ladresse un user
//}
}
