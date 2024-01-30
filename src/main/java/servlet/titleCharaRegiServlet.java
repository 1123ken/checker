package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;

/**
 * タイトルとキャラクターの登録処理を行うサーブレットです。
 * 画面遷移は行わず、タイトルDAOに値を渡してDBに登録を行います。
 * 
 * @author 馬場 健太朗
 * @version 1.0
 * @since Servlet 3.0
 */
@WebServlet("/titleCharaRegiServlet")
public class titleCharaRegiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POSTメソッドの処理です。
     * 
     * @param request  HTTPサーブレットリクエスト
     * @param response HTTPサーブレットレスポンス
     * @throws ServletException サーブレットの一般的な例外
     * @throws IOException      入出力関連の例外
     */
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
                
                //キャラクターが重複しているかの確認
                if (characterRegistrationSuccess) {
                	//trueなら登録
                    response.getWriter().write("キャラクターを新規登録しました");
                } else {
                	//falseなら
                    response.getWriter().write("すでにキャラクターが登録されています");
                }
            } else {
                // タイトルが存在しない場合は新しいタイトルとキャラクターを登録
                boolean titleRegistrationSuccess = titleDAO.registerTitle(addTitle);
                
                //
                if (titleRegistrationSuccess) {
                	//
                    boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));
                    
                    //
                    if (characterRegistrationSuccess) {
                    	//
                    	response.getWriter().write("キャラクターを新規登録しました");
                    } else {
                        //
                    	response.getWriter().write("すでにキャラクターが登録されています");
                    }
                } else {
                	//
                    response.getWriter().write("登録失敗しました");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("エラーが発生しました。");
        }
    }
}
