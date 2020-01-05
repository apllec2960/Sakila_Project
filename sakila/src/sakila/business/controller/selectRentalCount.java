package sakila.business.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.RentalDao;

/**
 * Servlet implementation class selectRentalCount
 */
@WebServlet("/business/selectRentalCount")
public class selectRentalCount extends HttpServlet {
	private RentalDao rentalDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		System.out.println("selectRentalCount Servlet");
		
		rentalDao = new RentalDao();
		
		int count = rentalDao.RentalCount();
		System.out.println("count"+count);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(count);
		
		response.getWriter().write(jsonStr);
	}

}
