package monpkg.impl;

import javax.interceptor.Interceptors;

@Interceptors({Interceptor.class})
public class LaClasseASurveiller  {

	public void methodePrenantduTemps(int sleepTime) throws InterruptedException{
		Thread.sleep(sleepTime);
	}
	
}