package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;

@WebServlet("/titleCharaRegiServlet")
public class titleCharaRegiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");

			//登録情報
			String addTitle = request.getParameter("addTitle");
			String addChara = request.getParameter("addChara");

			//インスタンスの作成
			TitleDAO titleDAO = new TitleDAO();

			//true or falseの処理
			boolean success = titleDAO.registerOrUpdateTitleAndCharacter(addTitle, addChara);

			if (success) {
				response.getWriter().write("登録が成功しました。");
			} else {
				response.getWriter().write("登録が失敗しました。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
