package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.db.DBHelper;

public class StaffDao {
	//스태프 테이블에 행의 수를 구하는 메소드
	public int selectStaffCount() {
		System.out.println("selectStaffCount Dao 실행");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM staff";
		int count =0;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs =stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return count;
	}
	
	//스태프 리스트 출력.
	public List<Staff> selectStaffList(){
		System.out.println("selectStaffList Dao 실행");
		List<Staff> list = new ArrayList<Staff>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT s.store_id, ad.address, CONCAT(st.first_name, ' ', st.last_name) staffName, s.last_update FROM store s \r\n" + 
					"INNER JOIN address ad \r\n" + 
					"INNER JOIN staff st\r\n" + 
					"ON s.address_id = ad.address_id\r\n" + 
					"AND s.manager_staff_id = st.staff_id";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Staff staff = new Staff();
				staff.setAddress(new Address());
				staff.setStore(new Store());
				
				staff.getStore().setStoreId(rs.getInt("s.store_id"));
				staff.getAddress().setAddress(rs.getString("ad.address"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setLastUpdate(rs.getString("s.last_update"));
				
				list.add(staff);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
}
