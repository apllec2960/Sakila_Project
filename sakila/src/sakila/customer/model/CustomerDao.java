package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.db.DBHelper;

public class CustomerDao {
	
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
