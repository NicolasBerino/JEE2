package monpkg.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import monpkg.services.StorageInterface;


@Singleton(name = "storage")
@Startup()
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@DependsOn("autreSingleton")
public class Storage implements StorageInterface{

    final Map<String, String> storage;

    public Storage() {
        storage = new HashMap<String, String>();
    }

    @PostConstruct
    public void init() {
        System.out.println("Storage running...");
    }

    /* Récupérer une valeur */
    @Lock(LockType.READ)
    public  String get(String key) {
    	System.out.println(storage.get(key));
        sleep(2000);
    	return storage.get(key);
    }

    /* Déposer une valeur */
    @Lock(LockType.WRITE)
    public  void set(String key, String value) {
    	storage.put(key, value);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
    
}
