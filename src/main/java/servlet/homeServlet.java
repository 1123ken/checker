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

import dao.TitleDAO;
import model.Title;

@WebServlet("/homeServlet")
public class homeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedTitle = request.getParameter("selectedTitle");

        if (selectedTitle != null && !selectedTitle.isEmpty()) {
            try {
                TitleDAO titleDAO = new TitleDAO();
                List<Title> titles = titleDAO.searchTitles(selectedTitle);

                // データをrequestスコープにセット
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
