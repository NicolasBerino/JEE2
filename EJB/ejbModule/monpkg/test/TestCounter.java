package monpkg.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Test;

import monpkg.entities.Counter;
import monpkg.services.BadCounter;
import monpkg.services.CounterManager;
import monpkg.services.CounterRemover;

public class TestCounter {
	
	   	@EJB
	    CounterManager cm;

	   	@Before
	   	public void begin() throws NamingException{
	   		EJBContainer.createEJBContainer().getContext().bind("inject", this);
	   	}

	   	
	    @Test
	    public void testCounterManager() throws NamingException, BadCounter {        
	    	assertNotNull(cm);
	        cm.createCounter("C1", 10);
	        Counter c = cm.getCounter("C1");
	        assertTrue(10 == c.getValue());
	    }
	    
	    @Test(expected = BadCounter.class)
	    public void testErrorCounter() throws BadCounter{
	        cm.createCounter("C2", -1);
	    }
	    
	    @Test
	    public void testRollBack(){
	        try {
				cm.createCounter("C2", 3);
				cm.createCounter("C2", -1);
			} catch (BadCounter e) {
			}
	        
	        assertTrue(cm.getCounter("C2").getValue() == 3);
	    }

	    @Test
	    public void testCounterManager2() throws NamingException, BadCounter {        
	    	assertNotNull(cm);
	        cm.createCounter2("C1", 10);
	        Counter c = cm.getCounter("C1");
	        assertTrue(10 == c.getValue());
	    }
	    
	    @Test(expected = BadCounter.class)
	    public void testErrorCounter2() throws BadCounter{
	        cm.createCounter2("C2", -1);
	    }
	    
	    @Test
	    public void testRollBack2(){
	        try {
	        	cm.createCounter2("C2", 3);
				cm.createCounter2("C2", -1);
			} catch (BadCounter e) {
			}
	        assertTrue(cm.getCounter("C2").getValue() == 3);
	    }
	    
	    @Test
	    public void testCounterManager3() throws NamingException, BadCounter {        
	    	assertNotNull(cm);
	        cm.removeAndCreateCounter("C1", 10);
	        Counter c = cm.getCounter("C1");
	        assertTrue(10 == c.getValue());
	    }
	    
	    @Test(expected = BadCounter.class)
	    public void testErrorCounter3() throws BadCounter{
	        cm.removeAndCreateCounter("C2", -1);
	    }
	    
	    @Test
	    public void testRollBack3() {
	        try {
	        	cm.removeAndCreateCounter("C2", 3);
				cm.removeAndCreateCounter("C2", -1);
			} catch (BadCounter e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        assertNull(cm.getCounter("C2"));
	    }
	    
	    @Test
	    public void testRCC(){
	    	CounterRemover cR = new CounterRemover();
	    	cR.removeCounterAndCommit("C1");
	    	assertNull(cm.getCounter("C1"));
	    }
}
