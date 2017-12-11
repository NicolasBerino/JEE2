package client;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import monpkg.entities.Counter;
import monpkg.impl.LongWorks;
import monpkg.services.BadCounter;
import monpkg.services.ConnectedUser;
import monpkg.services.CounterManager;
import monpkg.services.CounterRemover;
import monpkg.services.StorageInterface;
import monpkg.services.ToUpper;

import org.junit.Assert;
import org.junit.Test;

public class Client {

    @Test
    public void testRemoteToUpper() throws NamingException {
        // prepare client context
        Properties props = new Properties();
        String client = "org.apache.openejb.client.LocalInitialContextFactory";
        props.put(Context.INITIAL_CONTEXT_FACTORY, client);
        props.put(Context.PROVIDER_URL, "ejbd://localhost:4201");
        Context context = new InitialContext(props);
        // lookup EJB
        Object ref = context.lookup("toUpperLocal");
        

        Assert.assertTrue(ref instanceof ToUpper);
        ToUpper p = (ToUpper) ref;
        Assert.assertEquals("PREMIER", p.toUpper("premier"));
        
        ref = context.lookup("CounterRemoverLocalBean");
        Assert.assertTrue(ref instanceof CounterRemover);
        CounterRemover c = (CounterRemover) ref;
        c.removeCounterAndCommit("C1");
       
        // test and use it
        
        context.close();
    }

    @Test
    public void testLocalToUpper() throws NamingException {
        // prepare client context
        Context context = new InitialContext();
        // lookup EJB
        Object ref = context.lookup("toUpperLocal");
        // test and use it
        Assert.assertTrue(ref instanceof ToUpper);
        ToUpper p = (ToUpper) ref;
        Assert.assertEquals("PREMIER", p.toUpper("premier"));
        context.close();
       
    }
    
    @Test
    public void testWait() throws InterruptedException{
    	
    	Runnable codeThreadFils = new Runnable() {
    	    public void run() {
    	        Context context;
				try {
					context = new InitialContext();
		    	    Object ref = context.lookup("toUpperLocal");
		    	    ToUpper p = (ToUpper) ref;
		    	    p.wait2sec();
		    	    context.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	};
    	// code du thread père
    	Thread t = new Thread(codeThreadFils);
    	
    	long debut = System.currentTimeMillis();
    	t.start(); // demarrage du fils
    	t.join(); // attente de la mort du fils
    	long fin = System.currentTimeMillis();
    	Assert.assertTrue(fin-debut-2000<10);
    }
    
    @Test
    public void  TestWait2Thread() throws InterruptedException{
    	Runnable codeThreadFils = new Runnable() {
    	    public void run() {
    	        Context context;
				try {
					context = new InitialContext();
		    	    Object ref = context.lookup("toUpperLocal");
		    	    ToUpper p = (ToUpper) ref;
		    	    p.wait2sec();
		    	    context.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	};
    	// code du thread père
    	Thread t1 = new Thread(codeThreadFils);
    	Thread t2 = new Thread(codeThreadFils);
    	
    	long debut = System.currentTimeMillis();
    	t1.start(); // demarrage du fils
    	t2.start();
    	t1.join(); // attente de la mort du fils
    	t2.join();
    	long fin = System.currentTimeMillis();
    	Assert.assertTrue(fin-debut-2000<100);
    }
    
    @Test
    public void testConnectedUser() throws NamingException {
        Context context = new InitialContext();

        Object ref = context.lookup("connectedUserLocal");
        // test and use it
        Assert.assertTrue(ref instanceof ConnectedUser);
        ConnectedUser Cu = (ConnectedUser) ref;
        Cu.login("login","pwd");
        Cu.logout();
        context.close();
    }
    
    @Test
    public void testMultiConnected() throws InterruptedException{
    	Runnable codeThreadFils = new Runnable() {
    	    public void run() {
				try {
			        Context context = new InitialContext();
			        Object ref = context.lookup("connectedUserLocal");
			        // test and use it
			        Assert.assertTrue(ref instanceof ConnectedUser);
			        ConnectedUser Cu = (ConnectedUser) ref;
			        Cu.login("login","pwd");
			        Cu.logout();
			        context.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	};
    	Thread t1 = new Thread(codeThreadFils);
    	Thread t2 = new Thread(codeThreadFils);
    	
    	t1.start(); // demarrage du fils
    	t2.start();
    	t1.join(); // attente de la mort du fils
    	t2.join();
    	
    	
    }
    
    @Test(timeout = 7000)
	public void testReadAccess() throws Exception {

		Properties props = new Properties();
		String client = "org.apache.openejb.client.LocalInitialContextFactory";
	    props.put(Context.INITIAL_CONTEXT_FACTORY, client);
	    props.put(Context.PROVIDER_URL, "ejbd://localhost:4201");
	    Context context = new InitialContext(props);
		Object bean = context.lookup("storageRemote");
		Assert.assertTrue(bean instanceof StorageInterface);
		StorageInterface store = (StorageInterface) bean;
		store.set("prenom", "nom");
	    
	    Runnable execution = () -> {
	    		
		        store.get("prenom");
		};

	    ExecutorService exec = Executors.newFixedThreadPool(10);
	    exec.execute(execution);
	    exec.execute(execution);
	    exec.execute(execution);
	    exec.execute(execution);
	    // attente de la fin des threads
	    exec.shutdown();
	    exec.awaitTermination(10, TimeUnit.HOURS);
	}
    
    @Test(timeout = 6000)
    public void TestWork() throws NamingException, InterruptedException, ExecutionException, TimeoutException{
    	Properties props = new Properties();
		String client = "org.apache.openejb.client.LocalInitialContextFactory";
	    props.put(Context.INITIAL_CONTEXT_FACTORY, client);
	    props.put(Context.PROVIDER_URL, "ejbd://localhost:4201");
	    Context context = new InitialContext(props);
		Object bean = context.lookup("LongWorksLocalBean");
		LongWorks lW = (LongWorks) bean;
		lW.prepare();
		Future<String> test = lW.aLongWorkWithResult("test");
		System.out.println(test.isDone());
		Thread.sleep(1500);
		System.out.println(test.isDone());
		
		
//		Future<String> test =lW.aLongBreakableWorkWithResult("test");
//		
//		test.cancel(true);
//		while (! test.isDone()) {
//			
//		}
//		context.close();
    }
    
    
    
    
}