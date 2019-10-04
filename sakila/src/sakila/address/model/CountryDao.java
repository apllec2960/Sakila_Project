package sakila.address.model;

import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import sakila.db.DBHelper;

public class CountryDao {
	
	//country 테이블의 모든 행의 수를 구해주는 메소드
	public int selectCount() {
		int count =  0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) FROM country ";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();	//mariadb연결시켜주는 메소드  .getConnection()
			stmt = conn.prepareStatement(sql);	//쿼리를 저장할 변수 선언, 저장.
			rs = stmt.executeQuery();			//쿼리실행
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");	//모든 행의수를 count 변수에 저장.
			}
			//System.out.println("CountryDao count : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn); 	//모든 연결을 끊어주는 메소드 .close()
		}
		return count;
	}
	
	//Country테이블의 데이터를 리스트로 출력하는 메소드( )
	public List<Country> selectCountryList(int currentPage){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM country ORDER BY country_id DESC LIMIT ?,?";
		ResultSet rs = null;
		final int ROW_PER_PAGE =10;			//rowPerPage : 보여줄 행 (10으로 고정)
		int beginRow = (currentPage-1)*ROW_PER_PAGE; //beginRow : 시작하는 행
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);		//첫번쨰 ? 저장
			stmt.setInt(2, ROW_PER_PAGE);	//두번째 ? 저장
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	//country테이블에 데이터를 삽입할수있게 해주는 메소드
	public void insertCountry(Country country) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "Insert INTO country(country, last_update) VALUE(?, NOW())";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			rs = stmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		
	}
}
