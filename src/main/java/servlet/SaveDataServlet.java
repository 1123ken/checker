package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CharacterDAO;
import dao.SaveDataDAO;

/**
 * home.jspの入力フォームで入力したデータをSaveDataDAOに送るサーブレット
 * @author 馬場 健太朗
 */
@WebServlet("/SaveDataServlet")
public class SaveDataServlet extends HttpServlet {
	
    /**
     * doPostメソッドの処理です。
     * @param request  HTTPサーブレットリクエスト
     * @param response HTTPサーブレットレスポンス
     * @throws ServletException サーブレットの一般的な例外
     * @throws IOException      入出力関連の例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	//文字化けしないようにUTF-8で取得
    	request.setCharacterEncoding("utf-8");
    	
        // フォームからのデータを取得
    	String titleId = request.getParameter("titleId");
        String myCharacterName = request.getParameter("myCharacter");
        String yourCharacterName = request.getParameter("yourCharacter");
        String point = request.getParameter("point");
        String cpoint = request.getParameter("cpoint");
        
        // キャラクター名からキャラクターのIDをCharacterDAOを使用して取得
        int myCharacterId = CharacterDAO.getCharacterIdByName(myCharacterName);
        int yourCharacterId = CharacterDAO.getCharacterIdByName(yourCharacterName);

        // SaveDataDAOに値を渡してデータベースに保存
        SaveDataDAO saveDataDAO = new SaveDataDAO();
        saveDataDAO.saveData(Integer.parseInt(titleId), myCharacterId, yourCharacterId, point, cpoint);
        
        //入力後home.jspに戻る
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(request, response);
    }
}
