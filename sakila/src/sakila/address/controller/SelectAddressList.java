package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;

/**
 * Servlet implementation class selectAddressList
 */
@WebServlet("/address/selectAddressList")
public class SelectAddressList extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelectAddressListServlet 실행::");
		
		response.setContentType("application/json; charset=utf-8");
		addressDao = new AddressDao();
		
		List<Address> list = addressDao.selectAddressList();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		response.getWriter().write(jsonStr);
		
		
		
	}

}
