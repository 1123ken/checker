//DBに登録されているタイトルを検索してそれ専用ページに遷移するサーブレット


package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TitleDAO;
import model.Title;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedTitle = request.getParameter("selectedTitle");

		if (selectedTitle != null && !selectedTitle.isEmpty()) {
			try {
				// タイトル名をセッションに保存
				HttpSession session = request.getSession();
				session.setAttribute("selectedTitle", selectedTitle);

				// タイトル名を検索して必要な処理を行う（例：DBからデータを取得）
				TitleDAO titleDAO = new TitleDAO();
				List<Title> titles = titleDAO.searchTitles(selectedTitle);

				// タイトル情報をrequestスコープにセット
				request.setAttribute("titles", titles);

				// home.jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace(); // エラーハンドリングは必要に応じて追加
			}
		} else {
			// タイトルが指定されていない場合はトップページへリダイレクトなど
			response.sendRedirect(request.getContextPath() + "/top.jsp");
		}
	}

}
