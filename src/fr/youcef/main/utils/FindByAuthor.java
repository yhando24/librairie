package fr.youcef.main.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Author;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class FindByAuthor extends Action {


	@Override
	public boolean executeAction(HttpServletRequest request) {
		if(request.getMethod().equals("POST")) {
			
//			recuperer du nom a chercher
			String name = request.getParameter("book-by-author");
			
			boolean redirect = false;
			if(name != null) {
				
			// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
			// grace a un ecouteur devenement	
				
			EntityManager em = JpaUtil.getEntityManager();
			
			// selection de tout les auteurs qui correspond plus ou moin a la description name
			Query q = em.createQuery("SELECT a FROM Author a Where a.lastname  LIKE '%" + name + "%' OR a.firstname  LIKE '%" + name + "%'" );   


			try {
				
				List<Author> authors = q.getResultList();
				
				// on renvoi la list des auteurs qui correspondent
				request.getSession().setAttribute("booksbyauthor", authors);
				

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
