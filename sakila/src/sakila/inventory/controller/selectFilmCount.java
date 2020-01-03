package sakila.inventory.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.FilmDao;

/**
 * Servlet implementation class selectFilmCount
 */
@WebServlet("/inventory/selectFilmCount")
public class selectFilmCount extends HttpServlet {
	private FilmDao filmDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("selectFilmCountServlet 실행");
		response.setContentType("application/json; charset=utf-8 ");
		
		filmDao = new FilmDao();
		
		int count = filmDao.selectFilmCount();
		System.out.println("count"+count);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(count);
		
		response.getWriter().write(jsonStr);
	}

}
