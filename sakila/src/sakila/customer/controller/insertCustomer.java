package sakila.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.customer.service.CustomerService;

/**
 * Servlet implementation class insertCustomer
 */
@WebServlet("/insertCustomer")
public class insertCustomer extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//address request.getParameter();
		Address address = new Address();
		// customer request.getPar
		Customer customer = new Customer();
		
		customerService.insertCustomer(address, customer);



	}

}
