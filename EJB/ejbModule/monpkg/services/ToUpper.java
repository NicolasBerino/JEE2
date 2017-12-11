package monpkg.services;

import javax.ejb.Remote;

@Remote
public interface ToUpper {
   String toUpper(String data);
   
   void wait2sec();
}