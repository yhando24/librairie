package fr.youcef.main.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Book;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class FindByTitle extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		if(request.getMethod().equals("POST")) {
//			recuperer du titre a chercher
			String title = request.getParameter("book-by-title");
			
			boolean redirect = false;
			if(title != null) {
				
				// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli
				// grace a un ecouteur devenement
			EntityManager em = JpaUtil.getEntityManager();
			// selection de tout les auteurs qui correspond plus ou moin a la description name
			Query q = em.createQuery("SELECT b FROM Book b Where b.title  LIKE '%" + title + "%'");   
			

			try {
				
				List<Book> books = q.getResultList();
				
				// on renvoi la list des livres qui correspondent
				request.getSession().setAttribute("booksbytitle", books);
				

			}catch(Exception e) {
				
				e.printStackTrace();
			}
			em.close();
			return redirect;
		}
		}
		return false;
	}
}
