import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

public class test {

	
	public static void main(String[] args) {
		  try {
		    Hashtable<String,String> env = new Hashtable<String,String>();
		    env.put(InitialContext.INITIAL_CONTEXT_FACTORY,
		      "com.sun.jndi.ldap.LdapCtxFactory");
		    env.put(InitialContext.PROVIDER_URL,
		      "ldap://127.0.0.1:9999/dc=my-domain,dc=com");

		    Context ictx = new InitialContext(env);
		    NamingEnumeration<NameClassPair> e = ictx.list("cn=userC");
		    while (e.hasMore()) {
		      System.out.println("name: " + e.next().getName());
		    }
		  }
		  catch (javax.naming.NamingException e) {
		    System.err.println("Exception " + e);
		  }
		}
	
}
