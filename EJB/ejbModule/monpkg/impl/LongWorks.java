package monpkg.impl;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class LongWorks {

    @PostConstruct
    public void prepare() {
        System.out.println("Preparation de " + this);
    }

    @Asynchronous
    public void aLongWork() {
        for (int i = 0; (i < 10); i++) {
            sleep(1000);
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    @Asynchronous
    public Future<String> aLongWorkWithResult(String value) {
        sleep(1000);
        return new AsyncResult<String>(value);
    }

    @Resource
    private SessionContext context;

    @Asynchronous
    public Future<String> aLongBreakableWorkWithResult(String value) {
        for (int i = 0; (i < 10); i++) {
            System.out.print(i);
            sleep(5000);
            if (context.wasCancelCalled()) {
            	System.out.println("Travail annulé");
                return null;
            }
        }
        return new AsyncResult<String>(value);
    }
    
}