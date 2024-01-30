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

import beans.GameCharacter;
import beans.Title;
import dao.TitleDAO;

/**
 * top.jspで表示されたタイトル名からhome.jspに画面遷移する為のサーブレット
 * @author 馬場 健太朗
 */
@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	/** top.jspで選択したタイトルを取得 */
        String selectedTitle = request.getParameter("selectedTitle");

        if (selectedTitle != null && !selectedTitle.isEmpty()) {
            try {
            	/** セッションの取得 */
                HttpSession session = request.getSession();
                /** GameCharacterインスタンスの生成 */
                GameCharacter gc = new GameCharacter();
                /** セッションに選択したタイトル名を登録*/
                session.setAttribute("キャラクター情報",gc);
                /** titleDAOのインスタンスを生成 */
                TitleDAO titleDAO = new TitleDAO();
                /** 選択したタイトル名を引数にして検索したタイトル情報をtitlesに登録 */
                List<Title> titles = titleDAO.searchTitles(selectedTitle);
                /** 選択したタイトル名を使ってtitleIdに保存 */
                int titleId = titleDAO.getTitleId(selectedTitle);
                /** titleIdから登録されているキャラクターをcharactersリストに保存 */
                List<String> characters = titleDAO.getCharacters(titleId);
                /** gcに選択したタイトルを保存 */
                gc.setSelectedTitle(selectedTitle);
                /**選択タイトルをセッションに登録 */
                session.setAttribute("選択したタイトル名", selectedTitle);
                /**タイトルIDをセッションに登録 */
                session.setAttribute("タイトルID", titleId);
                /**　タイトル情報をセッションに登録 */
                session.setAttribute("タイトルリスト", titles);
                /** キャラクターリストをsessionスコープにセット */
                session.setAttribute("登録キャラクターリスト", characters);
                /**プレースホルダーに値をセット */
                gc.setSelectedTitle(selectedTitle);
                gc.setCharacters(characters);
                gc.setTitleId(titleId);
                /** home.jspへフォワード */
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            /** タイトルが指定されていない場合はトップページへ */
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/topServlet");
        	dispatcher.forward(request, response);
        }
    }
}
