package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Staff;
import sakila.business.model.StaffDao;

/**
 * Servlet implementation class selectStaffList
 */
@WebServlet("/business/selectStaffList")
public class selectStaffList extends HttpServlet {
	private StaffDao staffDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("selectStaffList Servlet 실행 ");
		response.setContentType("application/json; charset = utf-8"); 
		
		int currentPage =1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		staffDao = new StaffDao();
		List<Staff> list = staffDao.selectStaffList(currentPage);
		System.out.println(list.toString());
		
		//gson 선언
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.getWriter().write(jsonStr);
		}

}
