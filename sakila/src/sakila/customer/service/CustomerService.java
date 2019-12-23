package sakila.customer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private AddressDao addressDao;
	private CustomerDao customerDao;
	public void insertCustomer(Address address, Customer customer) {
		System.out.println("insertCustomer - Service");
		Connection conn = null;
		
	try {
		conn = DBHelper.getConnection();
		conn.setAutoCommit(false);
		//customer.gtAddress.setAddrss
		addressDao = new AddressDao();  
		int addressId = addressDao.insertAddress(address, conn);
		//System.out.println(addressId);
		customerDao = new CustomerDao();
		customer.setAddressId(addressId);
		customerDao.insertCustomer(conn,customer);
		conn.commit();
	}catch(Exception e) {
		e.printStackTrace();
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}finally {
			DBHelper.close(null, null, conn);
		}
	}
}

