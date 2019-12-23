package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.address.service.AddressService;
import sakila.customer.model.City;

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/address/insertAddress")
public class InsertAddress extends HttpServlet {
	private AddressService addressService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertAddressServlet 실행::");
		
		addressService = new AddressService();
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
		
		
		Address ad = new Address();
		ad.setAddress(address);
		ad.setAddress2(address2);
		ad.setCity(new City());
		ad.getCity().setCityId(cityId);
		ad.setDistrict(district);
		ad.setPostalCode(postalCode);
		ad.setPhone(phone);
		
		addressService.insertAddress(ad);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(null);;
		response.getWriter().write(jsonStr);
	}

}
