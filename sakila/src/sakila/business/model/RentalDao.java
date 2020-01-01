package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Customer;
import sakila.db.DBHelper;
import sakila.inventory.model.Film;
import sakila.inventory.model.Inventory;

public class RentalDao {
	//렌탈 테이블에 행의 수를 구하는 메소드
	public int selectRentalCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM rental";
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
	
	//렌탈리스트 출력 
	public List<Rental> selectRentalList(int currentPage, int rowPerPage){
		System.out.println("selectRntalList Dao");
		List<Rental> list = new ArrayList<Rental>();
		int beginRow = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT r.rental_id,f.title,CONCAT(c.first_name,'',c.last_name) AS customerName,CONCAT(s.first_name,'',s.last_name) AS staffName ,r.rental_date,r.return_date\r\n" + 
					"FROM rental r INNER JOIN customer c INNER JOIN staff s INNER JOIN inventory i INNER JOIN film f\r\n" + 
					"ON r.customer_id = c.customer_id AND r.staff_id = s.staff_id AND r.inventory_id = i.inventory_id AND f.film_id = i.film_id\r\n" + 
					"ORDER BY rental_id asc\r\n" + 
					"LIMIT ?, ?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			beginRow = (currentPage -1)*rowPerPage;
			System.out.println("beginRow : "+ beginRow);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			Rental rental = new Rental();
			rental.setCustomer(new Customer());
			rental.setFilm(new Film());
			rental.setInventory(new Inventory());
			
			rental.setRentalId(rs.getInt("r.rental_id"));
			rental.setCustomerName(rs.getString("customerName"));
			rental.setStaffName(rs.getString("staffName"));
			rental.getFilm().setTitle(rs.getString("f.title"));
			rental.setRentalDate(rs.getString("r.rental_date"));
			rental.setReturnDate(rs.getString("r.return_date"));
			
			list.add(rental);
			System.out.println("selectRentalDao list"+list.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	//렌탈 수 
	public int RentalCount() {
		System.out.println("rentalCount Dao 실행");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM rental";
		int count = 0;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("count :"+ count);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}
