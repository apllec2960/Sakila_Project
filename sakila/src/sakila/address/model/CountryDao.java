package sakila.address.model;

import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;

import sakila.customer.model.Country;
import sakila.db.DBHelper;

public class CountryDao {
	
	public List<Country> selectCountryListAll(){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT country_id, country FROM country ORDER BY country_id DESC";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				list.add(c);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	//country 테이블에 행의 수를 구하는 메소드
	public int selectCount() {
		int count =  0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) FROM country ";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();	//mariadb연결해주는 메소드  .getConnection()
			stmt = conn.prepareStatement(sql);	
			rs = stmt.executeQuery();			//쿼리 실행
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");	//coutry테이블의 행의 수를 count변수에 저장.
			}
			//System.out.println("CountryDao count : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn); 	
		}
		return count;
	}
	
	//Country리스트를 출력하게 만드는 메소드
	public List<Country> selectCountryList(int currentPage){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM country ORDER BY country_id DESC LIMIT ?,?";
		ResultSet rs = null;
		final int ROW_PER_PAGE =10;			//rowPerPage : ������ �� (10���� ����)
		int beginRow = (currentPage-1)*ROW_PER_PAGE; //beginRow : �����ϴ� ��
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);		//ù���� ? ����
			stmt.setInt(2, ROW_PER_PAGE);	//�ι�° ? ����
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
	
	//country테이블에 데이터를 입력하는 메소드
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
