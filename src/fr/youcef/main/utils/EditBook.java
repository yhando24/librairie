package fr.youcef.main.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Book;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class EditBook extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// recuperation de l'id du book a editer
	if(request.getMethod().equals("GET")) {
		int id = Integer.parseInt(request.getParameter("id"));
		// renvoi de l'id
		request.getSession().setAttribute("bookid", id);
	}else {
		// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
		// grace a un ecouteur devenement	
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		// recuperation du livre a editer grace a l id
		Book book = em.find(Book.class, id);
		
		// edition grace au formulaire
		book.setTitle(request.getParameter("book-title"));
		book.setOverview(request.getParameter("book-overview"));
		
		tr.begin();
		em.persist(book);
		tr.commit();
		
		request.getSession().setAttribute("bookid", -1);
	}
		
		return true;
	}

}
