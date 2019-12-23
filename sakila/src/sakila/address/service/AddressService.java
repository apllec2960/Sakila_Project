package sakila.address.service;

import java.sql.Connection;
import java.sql.SQLException;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.db.DBHelper;

public class AddressService {
	private AddressDao addressDao;
	public void insertAddress(Address address) {
		Connection conn= null;
		System.out.println("insertAddressService");
		
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);;
			addressDao = new AddressDao();
			addressDao.insertAddress(address,conn);
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}finally {
			DBHelper.close(null, null, conn);
		}
		
	}
}
