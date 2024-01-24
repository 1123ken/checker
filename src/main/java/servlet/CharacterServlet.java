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

@WebServlet("/CharacterServlet")
public class CharacterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CharacterDAO characterDAO = new CharacterDAO();

        // 選択されたタイトルのIDを取得
        int titleId = Integer.parseInt(request.getParameter("titleId"));

        // 特定のタイトルに紐づくキャラクターのリストを取得
        List<GameCharacter> characters = characterDAO.getAllCharactersForTitle(titleId);

        // キャラクターリストをリクエスト属性にセット
        request.setAttribute("characters", characters);

        // 画面遷移せずにJSON形式でレスポンスを返す
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(characters));
    }
}
