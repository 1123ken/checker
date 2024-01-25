package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CharacterDAO;

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

        // データベースに接続してデータを保存する処理
        try (
        		Connection connection = DriverManager.getConnection("jdbc:postgresql://your-database-url", "username", "password")) {
            
        	//キャラクター名からキャラクターのIDをCharacterDAOを使用して取得
            int myCharacterId = CharacterDAO.getCharacterIdByName(myCharacterName);
            int yourCharacterId = CharacterDAO.getCharacterIdByName(yourCharacterName);
            
            System.out.println("自キャラ"+ myCharacterId + "対策キャラ" + yourCharacterId);
            
            String sql = "INSERT INTO point (title_id, my_character_id, your_character_id, worl, point, cpoint) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(titleId));
                statement.setInt(2, myCharacterId);
                statement.setInt(3, yourCharacterId);
                statement.setString(4, result);
                statement.setString(5, point);
                statement.setString(6, cpoint);

                // データベースに挿入
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラー処理を行う
            response.sendRedirect("errorPage.jsp");
            return;
        }
        // データベースへの保存が成功した場合、ホームページにリダイレクト
        response.sendRedirect("home.jsp");
    }
}
