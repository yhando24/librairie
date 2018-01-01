package fr.youcef.main.utils;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Author;
import fr.youcef.model.beans.Book;
import fr.youcef.model.utils.Country;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class AddBook extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		if(request.getMethod().equals("POST")) {
		
		
//		creation du book a partir des paramatres de la request
	
		Book book = new Book(
				request.getParameter("book-title"),
				request.getParameter("book-overview"),
				Float.parseFloat(request.getParameter("book-price")),
				true,
				new ArrayList<Author> ()
		);
		
		// gestion de la disponibilite et du boolean en base de donnée
		try {
				if(request.getParameter("book-avaibility").contains("on")){
				
			book.setAvailable(true);
				}
	
			}catch(NullPointerException e) {
				book.setAvailable(false);
				
			}
		
		
		// ajout dun auteur au livre obtenu a partir des paramatres de la requetes
		
		Author author = new Author(
				request.getParameter("author-firstname"),
				request.getParameter("author-lastname"),
				Country.AFRIQUE);
		book.addAuthor(author);
		// insertion du livre en base de données
		
		
		// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
		// grace a un ecouteur devenement	
		
		EntityManager em = JpaUtil.getEntityManager();	
		
	

	try{
		em.getTransaction().begin();
		em.persist(book);
	
		em.getTransaction().commit();
	} catch (RollbackException e) {
		em.getTransaction().rollback();
	}
	em.close();
	
	 return true;
	}
		return false;
	} 
}
