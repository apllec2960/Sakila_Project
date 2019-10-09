package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.db.DBHelper;

public class FilmActorDao {
	public int selectFilmActorCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM film_actor";
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
}

