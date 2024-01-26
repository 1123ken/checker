package servlet;

//TOPページに画面遷移するためのサーブレット
//WEB-INF内にあるのでここからしか画面遷移できない

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;

@WebServlet("/topServlet")
public class topServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public topServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//登録タイトルを表示するためのDB接続処理
		TitleDAO titleDAO = new TitleDAO();
        List<String> titleList = titleDAO.getAllTitles();
        request.setAttribute("titleList", titleList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}
}
