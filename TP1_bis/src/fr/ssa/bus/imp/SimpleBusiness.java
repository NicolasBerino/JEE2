package fr.ssa.bus.imp;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.ssa.bus.IBusiness;
import fr.ssa.log.ILog;

@Service("business")
public class SimpleBusiness implements IBusiness {

	private ILog logger;
	
	@PostConstruct
	public void init() {
		if (logger == null) {
			throw new IllegalStateException("null logger");
		}
		logger.log("Start " + this);
	}
	
	@PreDestroy
	public void close() {
		logger.log("Stop " + this);
	}
	
	public int doAddition(int a, int b) {
		logger.log("doAddition(" + a + "," + b + ")");
		int somme = a+b;
		logger.log(""+somme);
		return (a + b);
	}
	
	public boolean doLogin(String name, String pass) {
		logger.log("doLogin(\"" + name + "\",\"" + pass + "\")");
		return (name.equals(pass));
	}
	
	public ILog getLogger() {
		return logger;
	}
	
	
	@Autowired
	@Qualifier("test")
	public void setLogger(ILog logger) {
		this.logger = logger;
	}
	
}