package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Beans;
import dao.CharacterDAO;

@WebServlet("/checker/GetDataServlet")
public class GetDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String myCharacter = request.getParameter("myCharacter");
        String yourCharacter = request.getParameter("yourCharacter");

        if (myCharacter != null && !myCharacter.isEmpty() && yourCharacter != null && !yourCharacter.isEmpty()) {
        	
            // 両方のキャラクターが指定された場合の処理
            CharacterDAO characterDAO = new CharacterDAO();
            List<Beans> beansList = characterDAO.getAllCharacterData(myCharacter, yourCharacter);

            // レスポンスにJSON形式でデータを返す
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(beansList);

            response.getWriter().write(json);
        } else if (myCharacter != null && !myCharacter.isEmpty()) {
        	
            // myCharacter のみが指定された場合の処理
            CharacterDAO characterDAO = new CharacterDAO();
            List<Beans> beansList = characterDAO.getAllCharacterData(myCharacter, myCharacter);

            // レスポンスにJSON形式でデータを返す
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(beansList);

            response.getWriter().write(json);
        } else {
            // myCharacter がnullまたは空の場合のエラーハンドリング
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("myCharacterの値がnullです");
        }
    }
}
