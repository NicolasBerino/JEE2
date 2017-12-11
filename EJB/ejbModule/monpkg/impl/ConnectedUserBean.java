package monpkg.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import monpkg.services.ConnectedUser;
import monpkg.services.ToUpper;

@Stateful(name="connectedUser")
@Local

public class ConnectedUserBean implements ConnectedUser {

	@EJB
	private ToUpper toUpper;
	
	private static int compteur;
	
	public void login(String login, String pwd) {
		if(login.equals("login") && pwd.equals("pwd")){
			System.out.println("Login user "+this);

			compteur ++ ;
			System.out.println(compteur);
		}
	}
        
   @PostConstruct()
   public void debut() {
	    System.out.println("EJB Stateful created " + this);
   }

   @PreDestroy()
   public void fin(){
	   System.out.println("EJB Stateful destroyed "+ this);
   }
   
   @Remove
   public void logout() {
      System.out.println("Logout user "+this);
   }
   
}