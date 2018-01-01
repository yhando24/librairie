package fr.youcef.pojo.utils;

import java.util.HashMap;
import java.util.Map;

import fr.youcef.main.utils.AddBook;
import fr.youcef.main.utils.DeleteBook;
import fr.youcef.main.utils.EditBook;
import fr.youcef.main.utils.FindByAuthor;
import fr.youcef.main.utils.FindById;
import fr.youcef.main.utils.FindByTitle;
import fr.youcef.main.utils.ListBooks;
import fr.youcef.main.utils.LoginAction;
import fr.youcef.main.utils.LogoutAction;
import fr.youcef.main.utils.SigninAction;
import fr.youcef.main.utils.addAuthor;

public final class ActionManager  { //final pour que personne puisse la modifier et personne la prenne en heritage
		
	
		public static final String ACTION_HOME ="home";
	    public static final String ACTION_ADD = "add";
		public static final String ACTION_DELETE ="delete";
		public static final String ACTION_EDIT ="edit";
		public static final String ACTION_LIST_BOOKS ="books";
		public static final String ACTION_FIND_ID ="findbyid";
		public static final String ACTION_FIND_TITLE ="findbytitle";
		public static final String ACTION_FIND_AUTHOR ="findbyauthor";
		public static final String ACTION_ADD_AUTHOR ="addauthor";
		public static final String ACTION_SIGN_IN_ ="signin";
		public static final String ACTION_LOG_IN ="login";
		public static final String ACTION_LOG_OUT ="logout";

		
	private ActionManager() {} // pour la protger et quon puisse pas linstancier
	
	private static Map <String, Action> actions;// recoit le nom de laction (actionname)string et cree un action
	
	static {
		actions = new HashMap<String, Action>(); 
		actions.put(ACTION_HOME, new HomeAction());
		actions.put(ACTION_ADD, new AddBook() );
		actions.put(ACTION_DELETE, new DeleteBook() );     // on met dans la map tout les actions et leurs cle (la string)
		actions.put(ACTION_EDIT, new EditBook() );			// des quon entre la cle on lance un new action en ffonction de la cle
		actions.put(ACTION_LIST_BOOKS, new ListBooks() );
		actions.put(ACTION_FIND_ID, new FindById() );
		actions.put(ACTION_FIND_TITLE, new FindByTitle() );
		actions.put(ACTION_FIND_AUTHOR, new FindByAuthor() );
		actions.put(ACTION_ADD_AUTHOR, new addAuthor() );
		actions.put(ACTION_SIGN_IN_, new SigninAction());
		actions.put(ACTION_LOG_IN, new LoginAction());
		actions.put(ACTION_LOG_OUT, new LogoutAction());
	}
	public static Action getAction(String actionName) {   // c la que en fonction de laction (string) envoyer par la frontservlet on fait une action
		return actions.get(actionName);				// get permet de recuperer l'acion de la map grace a la cle
	}
}
