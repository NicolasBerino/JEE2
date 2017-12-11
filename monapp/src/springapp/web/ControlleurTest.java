package springapp.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class ControlleurTest {

	static int compteur = 0;
		
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.getAttribute("compteur");
			compteur ++;
			ModelAndView MV = new ModelAndView("hello");
			return MV;

			}
	
}
