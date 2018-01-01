package fr.youcef.main.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.User;
import fr.youcef.pojo.utils.Action;
import fr.youcef.pojo.utils.Md5;
import fr.youcef.utils.JpaUtil;

public class SigninAction extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		
		request.setAttribute("title", "page d'inscription");
		
		
		
		if(request.getMethod().equals("POST")) {
			
		
			
		// Recuperation des donnees utilisateur	
			
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// hachage du mot de passe
		Md5 md = new Md5(password);
		password = md.codeGet();
		
		String role = request.getParameter("role");
		
		
	
		
		if(firstname != null && lastname != null && pseudo != null && password != null
			&& !firstname.isEmpty() && !lastname.isEmpty() 
			&& !pseudo.isEmpty() && !password.isEmpty()) {
			
			// creation de lutilisateur grace au information du formulaire
		
			User user = new User(firstname, lastname, pseudo, email, password, role);
		
			EntityManager em = JpaUtil.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			boolean redirect = false;
			
			try {
				transaction.begin();
				em.persist(user);
				transaction.commit();
				request.getSession().setAttribute("user", user);
				redirect = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
			User u2 = em.merge(user); /// clone en fonction de ce quon recuperer de la base de sessioon au cas ou
			request.getSession().setAttribute("u2", u2);
			em.close();
			return redirect;
		  }
		}
		return false;
	}
	

}
