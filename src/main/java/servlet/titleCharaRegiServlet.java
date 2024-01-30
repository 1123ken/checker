//titleDAOに値を渡してDBに登録処理をするサーブレット 
//値を渡すだけで画面遷移はしない

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;
/**
 * 
 * @author 馬場 健太朗
 *
 */
@WebServlet("/titleCharaRegiServlet")
public class titleCharaRegiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            // 登録情報
            String addTitle = request.getParameter("addTitle");
            String addChara = request.getParameter("addChara");

            // インスタンスの作成
            TitleDAO titleDAO = new TitleDAO();

            // タイトルが既に存在するか確認
            if (titleDAO.seachTitle(addTitle)) {
                // タイトルが既に存在する場合はキャラクターを登録
                boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));

                if (characterRegistrationSuccess) {
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("duplicateCharacter");
                }
            } else {
                // タイトルが存在しない場合は新しいタイトルとキャラクターを登録
                boolean titleRegistrationSuccess = titleDAO.registerTitle(addTitle);

                if (titleRegistrationSuccess) {
                    boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));

                    if (characterRegistrationSuccess) {
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("duplicateCharacter");
                    }
                } else {
                    response.getWriter().write("failure");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("エラーが発生しました。");
        }
    }
}

