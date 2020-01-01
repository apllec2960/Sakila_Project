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
	int rowPerPage = 10;
	int totalRow = 0;
	int lastPage = 0;
	
	rentalDao = new RentalDao();
	
	List<Rental> list = rentalDao.selectRentalList(currentPage, rowPerPage);
	System.out.println("list"+list);
	
	totalRow = rentalDao.RentalCount();
	if(totalRow % rowPerPage ==0) {
		totalRow = (totalRow/rowPerPage);
	}else {
		totalRow = (totalRow/rowPerPage)+1;
	}
	System.out.println("totalRow : "+ totalRow);
	
	Gson gson = new Gson();
	String jsonStr = gson.toJson(list);
	System.out.println(jsonStr.toString());
	
	response.getWriter().write(jsonStr);
	
	}

}
