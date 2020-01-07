package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class StoreDao {
	//store테이블에 행의 수를 구하는 메소드
	public int selectStoreCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM store";
		int count =0;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs =stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return count;
	}
	
	public List<Store> storeList(){
		List<Store> list= new ArrayList<Store>();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		String sql = "SELECT store_id From store";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		while(rs.next()) {
			Store store = new Store();
			
			store.setStoreId(rs.getInt("store_id"));
			list.add(store);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
}
