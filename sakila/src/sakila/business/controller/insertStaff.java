package sakila.business.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.business.model.Staff;
import sakila.business.model.StaffDao;
import sakila.business.model.Store;

/**
 * Servlet implementation class insertStaff
 */
@WebServlet("/business/insertStaff")
public class insertStaff extends HttpServlet {
	private StaffDao staffDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insertStaff Servlet 실행");
		response.setContentType("application/json;charset= utf-8");
		
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		
		System.out.println(storeId+"/"+addressId+"/"+firstName+"/"+lastName+"/"+email+"/"+userName);
		
		Staff staff = new Staff();
		staff.setStore(new Store());
		staff.setAddress(new Address());
		staff.getStore().setStoreId(storeId);
		staff.getAddress().setAddressId(addressId);
		staff.setFirstName(firstName);
		staff.setLastName(lastName);
		staff.setEmail(email);
		staff.setUserName(userName);

		System.out.println(staff.toString());
		staffDao = new StaffDao();
		staffDao.insertStaff(staff);
		
	}

}
