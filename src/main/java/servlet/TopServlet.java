package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;

/**
 * TOPページに画面遷移するためのサーブレットです。
 * WEB-INF内にあるため、ここからしか画面遷移できません。
 * @author 馬場 健太朗
 * @version 1.0
 * @since Servlet 3.0
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGETメソッドの処理です。
     * @param request  HTTPサーブレットリクエスト
     * @param response HTTPサーブレットレスポンス
     * @throws ServletException サーブレットの一般的な例外
     * @throws IOException      入出力関連の例外
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字化けを防ぐためにUTF-8で文字コードを登録
        request.setCharacterEncoding("UTF-8");

        // 登録タイトルを表示するためのDB接続処理
        TitleDAO titleDAO = new TitleDAO();
        // titleDAOのgetAllTitleメソッドを使用して登録されている全タイトルを取得
        List<String> titleList = titleDAO.getAllTitles();
        /** titleListをリクエストスコープに登録 */
        request.setAttribute("titleList", titleList);

        // 上記の処理後にtop.jspに画面遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
        dispatcher.forward(request, response);
    }
}