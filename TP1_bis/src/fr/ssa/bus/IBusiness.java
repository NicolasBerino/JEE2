package fr.ssa.bus;

import fr.ssa.log.ILog;

public interface IBusiness {
	
	int doAddition(int a, int b);
	
	boolean doLogin(String name, String pass);

	void setLogger(ILog logger);
	
}
