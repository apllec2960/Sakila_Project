package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CityDao;
import sakila.customer.model.City;

/**
 * Servlet implementation class SelectCityListByCountry
 */
@WebServlet("/address/selectCityListByCountry")
public class SelectCityListByCountry extends HttpServlet {
	public CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelectCityByCountryServlet 실행 ::");
		
		response.setContentType("application/json; charset=utf-8");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println("selectCityListCountry countryId : "+ countryId);
		
		cityDao = new CityDao();
		List<City> list = cityDao.selectCityListByCountry(countryId);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		response.getWriter().write(jsonStr);
		//System.out.println(jsonStr);

	}
}
