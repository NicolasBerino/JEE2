package monpkg.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import monpkg.entities.Counter;
import monpkg.impl.Interceptor;


@Interceptors({Interceptor.class})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CounterManager {

    @PersistenceContext(unitName = "myMySQLBase")
    EntityManager em;

    public Counter getCounter(String name) {
        return em.find(Counter.class, name);
    }

    public void removeCounter(String name) {
        Counter c = em.find(Counter.class, name);
        if (c != null) {
            em.remove(c);
        }
    }

    public void createCounter(String name, Integer value) throws BadCounter {
        removeCounter(name);
        Counter c = new Counter();
        c.setName(name);
        c.setValue(value);
        em.persist(c);
        if (value < 0) {
            throw new BadCounter("pas moins de zéro");
        }
    }

    
    @EJB
    CounterRemover cr;

    public void createCounter2(String name, Integer value) throws BadCounter {
        cr.removeCounter(name);
        Counter c = new Counter();
        c.setName(name);
        c.setValue(value);
        em.persist(c);
        if (value < 0) {
            throw new BadCounter("pas moins de zéro");
        }
    }
    
    public void removeAndCreateCounter(String name, Integer value) throws BadCounter {
        cr.removeCounterAndCommit(name);
        Counter c = new Counter();
        c.setName(name);
        c.setValue(value);
        em.persist(c);
        if (value < 0) {
            throw new BadCounter("pas moins de zéro");
        }
    }
    

    
    
}