package sakila.address.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import sakila.customer.model.City;
import sakila.db.DBHelper;

public class AddressDao {
	
	
	
	//address테이블에 데이터를 삽입하는 메소드
	public int insertAddress( Address address, Connection conn) {
		System.out.println("insertAddressDao");
		System.out.println(address.toString());
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO address(address,address2,city_id,district,postal_code,phone, last_update) "
					+ "VALUES(?,?,?,?,?,?, now())";
		System.out.println(address.getCity().getCityId());
		try {
			
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,address.getAddress());
			stmt.setString(2,address.getAddress2());
			stmt.setInt(3,address.getCity().getCityId());
			stmt.setString(4,address.getDistrict());
			stmt.setString(5,address.getPostalCode());
			stmt.setString(6,address.getPhone());
			
			stmt.executeUpdate();
			
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(null, stmt, conn);
		}
		return 0;
	}
	
	
	//address 테이블에 데이터를 리스트로 출력하는 메소드
	public List<Address> selectAddressList(){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.address_id, a.address, a.address2, a.district, ct.city_id, a.postal_code, "
				+ "a.phone, a.last_update FROM address a INNER JOIN city ct ON ct.city_id = a.city_id "
				+ "ORDER BY a.last_update DESC LIMIT 10";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				Address a = new Address();
				a.setCity(new City());
				
				a.setAddressId(rs.getInt("a.address_id"));
				a.setAddress(rs.getString("a.address"));
				a.setAddress2(rs.getString("a.address2"));
				a.setDistrict(rs.getNString("a.district"));
				a.getCity().setCityId(rs.getInt("ct.city_id"));
				a.setPostalCode(rs.getString("a.postal_code"));
				a.setPhone(rs.getString("a.phone"));
				a.setLastUpdate(rs.getString("a.last_update"));
				list.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}

	//address 테이블에 행의 수를 구하는 메소드
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM address";
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
