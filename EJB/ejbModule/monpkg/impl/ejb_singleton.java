package monpkg.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;



@Singleton(name = "autreSingleton")
@Startup()
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)

public class ejb_singleton {

	@PostConstruct
    public void init() {
        System.out.println("Singleton running...");
    }
	
}
