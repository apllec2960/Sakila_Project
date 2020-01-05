package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Rental;
import sakila.business.model.RentalDao;

/**
 * Servlet implementation class selectRentalList
 */
@WebServlet("/business/selectRentalList")
public class selectRentalList extends HttpServlet {
	private RentalDao rentalDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("application/json; charset = utf-8");
	System.out.println("selectRentalList Servlet 실행");
	
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println("currentPage"+currentPage);
	
	rentalDao = new RentalDao();
	
	List<Rental> list = rentalDao.selectRentalList(currentPage);
	System.out.println("list"+list);
		
	Gson gson = new Gson();
	String jsonStr = gson.toJson(list);
	System.out.println(jsonStr.toString());
	
	response.getWriter().write(jsonStr);
	
	}

}
