package sakila.business.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.StaffDao;

/**
 * Servlet implementation class selectStaffCount
 */
@WebServlet("/business/selectStaffCount")
public class selectStaffCount extends HttpServlet {
	private StaffDao staffDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		System.out.println("selectStaffCount Servlet");
		
		//스테프 수 출력.
		int count = staffDao.selectStaffCount();
		System.out.println("count"+ count);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(count);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
	}


}
