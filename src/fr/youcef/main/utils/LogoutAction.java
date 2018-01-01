package fr.youcef.main.utils;

import javax.servlet.http.HttpServletRequest;

import fr.youcef.pojo.utils.Action;

public class LogoutAction extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// deconnection = suppression du user
		
		request.getSession().setAttribute("user", null);
		
		// redirection vers l'acceuil
	
	
		
		return false;
		}
	
}
