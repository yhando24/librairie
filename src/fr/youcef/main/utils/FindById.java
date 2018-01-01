package fr.youcef.main.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import com.mysql.fabric.Response;

import fr.youcef.model.beans.Book;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class FindById extends Action{

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(request.getMethod().equals("post"))
			
			{	 // on recuperer lid en question
				String idStr = request.getParameter("book-by-id");
				if(idStr != null) {
					try {
						int id = Integer.parseInt(idStr);
						// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
						// grace a un ecouteur devenement
						EntityManager em = JpaUtil.getEntityManager();
						EntityTransaction tr = em.getTransaction();
						
						// recuperation du livre grace a lid
						Book book = em.find(Book.class, id);
						// envoi du livre en question
					request.getSession().setAttribute("bookbyid", book);
					
				}	catch (Exception e) {
					// TODO: handle exception
				}
				}
			}return false;
}	
}

