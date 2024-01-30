package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.GameCharacter;
import dao.CharacterDAO;

/**
 * キャラクター情報を取得するためのサーブレットです。
 * @author 馬場 健太朗
 */
@WebServlet("/CharacterServlet")
public class CharacterServlet extends HttpServlet {
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
        // キャラクターDAOのインスタンスを生成
        CharacterDAO characterDAO = new CharacterDAO();

        // 選択されたタイトルのIDを取得
        int titleId = Integer.parseInt(request.getParameter("titleId"));

        // 特定のタイトルに紐づくキャラクターのリストを取得
        List<GameCharacter> characters = characterDAO.getAllCharactersForTitle(titleId);

        // キャラクターリストをJSON形式でレスポンスとして返す
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(characters));
    }
}
