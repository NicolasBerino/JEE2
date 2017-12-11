package monpkg.impl;

import javax.ejb.Stateless;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;

import monpkg.services.ToUpper;

@Stateless(name = "toUpper", description = "Un premier essai")
@Local
public class ToUpperImpl implements ToUpper {

   public String toUpper(String qui) {
      return qui.toUpperCase();
   }

   @PostConstruct()
   public void debut() {
      System.out.println("My Starting " + this);
   }

   @PreDestroy
   public void fin() {
      System.out.println("My Stopping " + this);
   }

	public void wait2sec() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}

