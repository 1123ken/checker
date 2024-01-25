package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CharacterDAO;
import dao.SaveDataDAO;

@WebServlet("/SaveDataServlet")
public class SaveDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // フォームからのデータを取得
        String titleId = request.getParameter("titleId");
        String myCharacterName = request.getParameter("myCharacter");
        String yourCharacterName = request.getParameter("yourCharacter");
        String result = request.getParameter("result");
        String point = request.getParameter("point");
        String cpoint = request.getParameter("cpoint");

        // キャラクター名からキャラクターのIDをCharacterDAOを使用して取得
        int myCharacterId = CharacterDAO.getCharacterIdByName(myCharacterName);
        int yourCharacterId = CharacterDAO.getCharacterIdByName(yourCharacterName);

        // SaveDataDAOに値を渡してデータベースに保存
        SaveDataDAO saveDataDAO = new SaveDataDAO();
        saveDataDAO.saveData(Integer.parseInt(titleId), myCharacterId, yourCharacterId, result, point, cpoint);

        // データベースへの保存が成功した場合、ホームページにリダイレクト
        response.sendRedirect("home.jsp");
    }
}
