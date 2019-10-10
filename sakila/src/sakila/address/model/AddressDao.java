package sakila.address.model;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.City;
import sakila.db.DBHelper;

public class AddressDao {
	
	
	
	//address테이블에 데이터를 삽입하는 메소드
	public int insertAddress(Connection conn, Address address) throws Exception {
		int addressId = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO address(address_id,address,address2,city_id,district,postal_code,phone, last_update,) VALUES(?,?,?,?,?,?,?, now())";
		
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,address.getAddressId());
			stmt.setString(2,address.getAddress());
			stmt.setString(3,address.getAddress2());
			stmt.setInt(4,address.getCity().getCityId());
			stmt.setString(5,address.getDistrict());
			stmt.setString(6,address.getPostalCode());
			stmt.setString(7,address.getPhone());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId= rs.getInt(1);
			}
		
		return addressId;
	}
	
	
	//address 테이블에 데이터를 리스트로 출력하는 메소드
	public List<Address> selectAddressList(){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.address_id, a.address, a.address2, a.district, ct.city_id, a.postal_code, "
				+ "a.phone, a.last_update FROM address a INNER JOIN city ct ON ct.city_id = a.city_id LIMIT 10";
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
