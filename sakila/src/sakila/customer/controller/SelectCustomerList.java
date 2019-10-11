package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;

/**
 * Servlet implementation class SelectCustomerList
 */
@WebServlet("/customer/selectCustomerList")
public class SelectCustomerList extends HttpServlet {
	CustomerDao customerDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("application/json ; charset =utf-8");
	System.out.println("selectCustomerListServlet 실행 ::");
	customerDao = new CustomerDao();
	
	List<Customer> list = customerDao.selectCustomerList();
	
	Gson gson = new Gson();
	String jsonStr = gson.toJson(list);
	System.out.println("selectCustomerList Servlet jsonStr ::"+jsonStr);
	response.getWriter().write(jsonStr);
	
		
	}

}
