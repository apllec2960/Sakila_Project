package sakila.inventory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.Film;
import sakila.inventory.model.FilmDao;

/**
 * Servlet implementation class selectFilmList
 */
@WebServlet("/inventory/selectFilmList")
public class selectFilmList extends HttpServlet {
	private FilmDao filmDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("selectFilmList Servlet");
		response.setContentType("application/json");
		
		System.out.println("currentPage : " + request.getParameter("currentPage"));
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		filmDao = new FilmDao();
		List<Film> list = filmDao.selectFilmList(currentPage);
		System.out.println("list"+ list.toString());
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.getWriter().write(jsonStr);
	}

}
