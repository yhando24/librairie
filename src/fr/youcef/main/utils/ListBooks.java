package fr.youcef.main.utils;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Book;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil; 
// classes en dessous de l'action manager
public class ListBooks extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
	
		String select = request.getParameter("tri");												
		
		// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
		// grace a un ecouteur devenement
		
		EntityManager em = JpaUtil.getEntityManager();
		
		// selection de tout les livres
		Query q = em.createQuery("SELECT b FROM Book b");
	
		List<Book> books = q.getResultList();
		
		// renvoi des livres
		request.setAttribute("books", books);
	
		return false;
	

  

	
		
	}
}
	

