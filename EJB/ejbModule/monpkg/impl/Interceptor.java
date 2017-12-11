package monpkg.impl;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor {

	
   @AroundInvoke
   public Object interceptor(InvocationContext context) throws Exception {
	   String methodName = context.getMethod().getName();
	   System.err.println("appel de " + methodName);
	   for (Object param : context.getParameters()) {
	      System.err.println("param = " + param.toString());
	   }
	   return context.proceed();
   }

}