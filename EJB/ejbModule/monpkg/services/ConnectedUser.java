package monpkg.services;

import javax.ejb.Remote;

@Remote
public interface ConnectedUser {
   void login(String login, String pwd);

   void logout();
}

