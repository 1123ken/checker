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

            // タイトルを登録
            boolean titleRegistrationSuccess = titleDAO.registerTitle(addTitle);

            // キャラクターを登録
            boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));

            if (titleRegistrationSuccess && characterRegistrationSuccess) {
                response.getWriter().write("登録が成功しました。");
            } else {
                response.getWriter().write("タイトルやキャラクターの登録に失敗しました。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
