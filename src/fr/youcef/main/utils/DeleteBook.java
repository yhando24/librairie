package fr.youcef.main.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Book;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class DeleteBook extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		// on recuperer l id en question
	String idStr = request.getParameter("id"); 
		if(idStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
				// grace a un ecouteur devenement	
				EntityManager em = JpaUtil.getEntityManager();
				EntityTransaction tr = em.getTransaction();
				
				Book book = em.find(Book.class, id);
				
				try{
					tr.begin();
					em.remove(book);
					tr.commit();
				}catch (Exception e) {
					tr.rollback();
					e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		return true;
	}

}
