package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.business.model.Store;
import sakila.db.DBHelper;

public class CustomerDao {
	
	public List<Customer> selectCustomerList(){
		System.out.println("selectCustomerList");
		List<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT ad.city_id, ad.address, ad.address2, ad.district, ad.postal_code, ad.phone, c.store_id, c.first_name, c.last_name, c.email "
					+ "FROM customer c INNER JOIN address ad "
					+ "ON c.address_id = ad.address_id LIMIT 10";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setStore(new Store());
				
				c.getStore().setStoreId(rs.getInt("c.store_id"));
				c.setFirstName(rs.getString("c.first_name"));
				c.setLastName(rs.getString("c.last_name"));
				c.setEmail(rs.getString("c.email"));
				
				c.setAddress(new Address());
				c.getAddress().setAddress(rs.getString("ad.address"));
				c.getAddress().setAddress2(rs.getString("ad.address2"));
				c.getAddress().setDistrict(rs.getString("ad.district"));		
				c.getAddress().setPostalCode(rs.getString("ad.postal_code"));
				c.getAddress().setPhone(rs.getString("ad.phone"));
				c.getAddress().setCity(new City());
				c.getAddress().getCity().setCityId(rs.getInt("ad.city_id"));
				list.add(c);
			}
			System.out.println("list"+list);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	//customer테이블의 데이터를 입력하는 메소드
	public void insertCustomer(Connection conn, Customer customer) throws Exception {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO customer("
				+ "store_id, first_name, last_name, email, address_id, active, create_date, last_update)"
				+ " VALUES(?,?,?,?,?,1,now(), now())";
			
			System.out.println("insertCustomer" + customer.getAddressId());
			
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,customer.getStoreId());
			stmt.setString(2,customer.getFirstName());
			stmt.setString(3,customer.getLastName());
			stmt.setString(4,customer.getEmail());
			stmt.setInt(5,customer.getAddressId());
			
			stmt.executeUpdate();
			stmt.close();
	}
	
	//customer테이블에 행의 수를 구하는 메소드
	public int selectCustomerCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM customer";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}
