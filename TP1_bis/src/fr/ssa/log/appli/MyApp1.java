package fr.ssa.log.appli;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import fr.ssa.bus.IBusiness;
import fr.ssa.bus.imp.SimpleBusiness;
import fr.ssa.log.ILog;
import fr.ssa.log.imp.BeanFileLogger;
import fr.ssa.log.imp.FileLogger;
import fr.ssa.log.imp.StderrLogger;

@SuppressWarnings("unused")
public class MyApp1 {
	
	// Utilisation du service "logger"	
	void use(ILog logger) {
		logger.log("result = $1", "hello");
	}
	
	void use(IBusiness business){
		business.doAddition(1, 2);
	}
	
	// integration
	void run() {
		//1ere implémentation
		//StderrLogger logger = new StderrLogger();
		
		//2eme implémentation
		//FileLogger logger = new FileLogger("/tmp/myapp.log"); 
		
		//3eme implémentation
		/*BeanFileLogger logger = new BeanFileLogger();
		logger.setFileName("/tmp/myapp2.log");*/
		
		/*logger.init();
		use(logger);
		logger.close();*/
		
		//4eme implementation
		// creation de la couche logger
		// creation de la couche logger
		/*BeanFileLogger logger = new BeanFileLogger();
		logger.setFileName("/tmp/myapp.log");
		logger.init();
		// creation de la couche metier			
		SimpleBusiness business = new SimpleBusiness();
		business.setLogger(logger);
		business.init();
		// utiliser la couche metier
		use(business);
		// fermer la couche metier
		business.close();
		// fermer la couche logger
		logger.close();*/
		
		//Implementation avec Spring
		String conf = "spring.xml";
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(conf);
		context.registerShutdownHook();
		// recuperer les beans
		//ILog logger = context.getBean(ILog.class);
		IBusiness business= context.getBean("business",IBusiness.class);
		use(business);
		

		
	}
	
	
	public static void main(String[] args) {
		MyApp1 app = new MyApp1();
		app.run();
	}
}