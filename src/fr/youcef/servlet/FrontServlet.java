package fr.youcef.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.youcef.pojo.utils.ActionManager;

@WebServlet(name = "FrontServlet", value = { "/home", "/books", "/books/add", "/books/addauthor", "/books/delete",
		"/books/edit", "/findbyid", "/findbytitle", "/findbyauthor", "/signin", "/login", "/logout" })
/**
 * Servlet implementation class Principal
 */

public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/home.jsp";
	private static final String PAGE_EDIT = "/WEB-INF/edit.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		recuperation de l'action name
		String actionName = getActionName(request);
		
//		Gestion d'un boolean par chaque action gerer pas laction manager.
//		le boolean lance une redirection sur la page des livres ou sur lactionnanme books
		boolean redirect = ActionManager.getAction(actionName).executeAction(request);
		
//		envoi en attribut de lactioname pour gerer les affichages en fonction de ce quon renvoi
		request.setAttribute("actionname", actionName);

		if (redirect) {
			System.out.println("actioname = " + actionName);

			response.sendRedirect(request.getContextPath() + "/books");
		} else {
//				redirection a la page d'accueil si l'utilisateur se deconnecte
			if (actionName.equals("logout")) {
				response.sendRedirect(request.getContextPath() + "/home");
			} else {
				
				getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);

			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		recuperation de l'action name
		String actionName = getActionName(request);
		
//		Gestion d'un boolean par chaque action gerer pas laction manager.
//		le boolean lance une redirection sur la page des livres ou sur lactionnanme 
		boolean redirect = ActionManager.getAction(actionName).executeAction(request);

		request.setAttribute("actionname", actionName);
		if (redirect) {
			System.out.println("actioname = " + actionName);
			System.out.println("je suis dans le redirect du post");
			response.sendRedirect(request.getContextPath() + "/books");
		} else {

			response.sendRedirect(request.getContextPath() + "/" + actionName);

		}

	}

	private String getActionName(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1); 
		return uri;
	}

}
