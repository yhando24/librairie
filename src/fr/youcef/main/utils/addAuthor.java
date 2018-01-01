package fr.youcef.main.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.youcef.model.beans.Author;
import fr.youcef.model.beans.Book;
import fr.youcef.model.utils.Country;
import fr.youcef.pojo.utils.Action;
import fr.youcef.utils.JpaUtil;

public class addAuthor extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(request.getMethod().equals("GET")) {
			// recuperation de l'id du book grace au lien url
			int id = Integer.parseInt(request.getParameter("id"));
			
			// renvoi de l'id
			request.getSession().setAttribute("bookidforA", id);
			
			// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
			// grace a un ecouteur devenement
			EntityManager em = JpaUtil.getEntityManager();
			EntityTransaction tr = em.getTransaction();
			
			// 	recuperer du livre grace a l'id
			Book book = em.find(Book.class, id);
			
			// envoi du livre
			request.getSession().setAttribute("bookforA", book);
			return false;
		}else {
			
			// connection a la base de donnée, recuperation de l EMF grace a jpa util. la creation de EMF est faite au lancement de lappli7
			// grace a un ecouteur devenement
			EntityManager em = JpaUtil.getEntityManager();
			EntityTransaction tr = em.getTransaction();
			
			//	recuperer du livre grace a l'id
			int id = Integer.parseInt(request.getParameter("id"));
			Book book = em.find(Book.class, id);
			
			//	creation d'un auteur grace au information du formulaire
			Author author = new Author(
					request.getParameter("author-firstname"),
					request.getParameter("author-lastname"),
					Country.ESPAGNOL
					
			);
			
			// boolean qui sert a verifier si rajouter l'auteur ou pas
			boolean add = false;
			
			// on recupere tout les auteurs et on passe le boolean a true si lauteur nexiste pas
			List <Author> authors =  book.getAuthors();
			for ( Author authorbook : authors) {
				if(authorbook.getFirstname() != author.getFirstname() && author.getLastname() != authorbook.getLastname()) {
					add = true;
				}
				
			}
			if(add) // ajout de lauteur
			{
					book.addAuthor(author);
					}
			
			tr.begin();
			em.persist(book);
			tr.commit();
			// on passe le bookid a -1 pour ne plus rentrer dans le cas
			request.getSession().setAttribute("bookid", -1);
			}
			
			return true;
		};
}


