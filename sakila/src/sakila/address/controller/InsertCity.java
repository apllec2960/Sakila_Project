package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.CityDao;
import sakila.customer.model.City;
import sakila.customer.model.Country;

/**
 * Servlet implementation class InsertCity
 */
@WebServlet("/address/insertCity")
public class InsertCity extends HttpServlet {
	private CityDao cityDao;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertCityServlet 실행 ::");
		
		String city = request.getParameter("city");
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		
		System.out.println("city : " + city);
		System.out.println("country : " + countryId);
		
		cityDao = new CityDao();
		City c = new City();
		c.setCity(city);
		c.setCountry(new Country());
		c.getCountry().setCountryId(countryId);
		cityDao.insertCity(c);
		
	}

}
