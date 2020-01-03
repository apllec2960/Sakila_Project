package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class FilmDao {
	public int selectFilmCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM film";
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
	
	public List<Film> selectFilmList(int currentPage){
		System.out.println("selectFilmList Dao");
		List<Film> list = new ArrayList<Film>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int rowPerPage = 10;
		int beginRow = 0;
			beginRow =(currentPage-1)*rowPerPage;
		String sql = "SELECT f.film_id, f.title ,CONCAT(substring(f.description,1,5),'..') AS description,"
					+ " f.release_year, l.name, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.last_update\r\n" 
					+"FROM film f inner join language L\r\n"  
					+"ON f.language_id = l.language_id\r\n" 
					+"ORDER BY f.film_id\r\n"  
					+"LIMIT ?,?";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Film film = new Film();
				film.setLanguage(new Language());
				
				film.setFilmId(rs.getInt("f.film_id"));
				film.setTitle(rs.getString("f.title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getString("f.release_year"));
				film.getLanguage().setName(rs.getString("l.name"));
				film.setRentalDuration(rs.getInt("f.rental_duration"));
				film.setRentalRate(rs.getDouble("f.rental_rate"));
				film.setLength(rs.getInt("f.length"));
				film.setReplacementCost(rs.getInt("f.replacement_cost"));
				film.setRating(rs.getString("f.rating"));
				film.setLastUpdate(rs.getString("f.last_update"));
				list.add(film);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
}
