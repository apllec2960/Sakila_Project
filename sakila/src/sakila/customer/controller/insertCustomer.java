package sakila.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.City;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.customer.service.CustomerService;

/**
 * Servlet implementation class insertCustomer
 */
@WebServlet("/customer/insertCustomer")
public class insertCustomer extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		System.out.println("insertCustomerServlet 실행 ::");
		customerService = new CustomerService();
		
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		System.out.println("insert cityId :" +cityId);
		System.out.println("insert address :" +address);
		System.out.println("insert address2 :" +address2);
		System.out.println("insert district :" +district);
		System.out.println("insert postalCode :" +postalCode);
		System.out.println("insert phone :" +phone);
		//address request.getParameter();
		
		Address ad = new Address();
		
		ad.setAddress(address);
		ad.setAddress2(address2);
		ad.setCity(new City());
		ad.getCity().setCityId(cityId);
		ad.setDistrict(district);
		ad.setPostalCode(postalCode);
		ad.setPhone(phone);
		
		
		// customer request.getPar
		Customer cu = new Customer();
		cu.setStoreId(Integer.parseInt(request.getParameter("storeId")));
		cu.setFirstName(request.getParameter("firstName"));
		cu.setLastName(request.getParameter("lastName"));
		cu.setEmail(request.getParameter("emailName"));
		
		System.out.println(request.getParameter("storeId"));
		System.out.println(request.getParameter("firstName"));
		System.out.println(request.getParameter("lastName"));
		System.out.println(request.getParameter("emailName"));
		
		customerService.insertCustomer(ad, cu);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(null);
		response.getWriter().write(jsonStr);

	}

}
