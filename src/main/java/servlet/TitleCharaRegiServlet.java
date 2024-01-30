package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TitleDAO;
/**
 * top.jspでタイトルやキャラクターを登録する際にデータの受け渡しをするサーブレット
 * @author 馬場 健太朗
 */
@WebServlet("/TitleCharaRegiServlet")
public class TitleCharaRegiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * doPostメソッドの処理です。
     * @param request  HTTPサーブレットリクエスト
     * @param response HTTPサーブレットレスポンス
     * @throws ServletException サーブレットの一般的な例外
     * @throws IOException      入出力関連の例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // レスポンスの文字エンコーディングを設定
            response.setCharacterEncoding("UTF-8");
            // フォームから送信された登録情報を取得
            String addTitle = request.getParameter("addTitle");
            String addChara = request.getParameter("addChara");
            // タイトルDAOのインスタンスを作成
            TitleDAO titleDAO = new TitleDAO();

            // タイトルが既に存在するか確認
            if (titleDAO.seachTitle(addTitle)) {
                // タイトルが既に存在する場合はキャラクターを登録
                boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));
                // キャラクターが重複しているかの確認
                if (characterRegistrationSuccess) {
                    // キャラクターが新規登録できた場合
                    response.getWriter().write("キャラクターを新規登録しました");
                } else {
                    // キャラクターがすでに登録されている場合
                    response.getWriter().write("すでに登録されています");
                }
            } else {
                // タイトルが存在しない場合は新しいタイトルとキャラクターを登録
                boolean titleRegistrationSuccess = titleDAO.registerTitle(addTitle);
                
                // タイトルが正常に登録されたか確認
                if (titleRegistrationSuccess) {
                    // タイトルが正常に登録された場合、キャラクターを登録
                    boolean characterRegistrationSuccess = titleDAO.registerCharacter(addChara, titleDAO.getTitleId(addTitle));
                    // キャラクターが正常に登録されたか確認
                    if (characterRegistrationSuccess) {
                        // タイトルとキャラクターが両方とも新規登録の場合
                        response.getWriter().write("タイトルとキャラクターを新規登録しました");
                    } else {
                        // キャラクターがすでに登録されている場合
                        response.getWriter().write("すでに登録されています");
                    }
                } else {
                    // 登録失敗時の表示
                    response.getWriter().write("登録失敗しました");
                }
            }
        } catch (Exception e) {
            // 例外が発生した場合のエラーハンドリング
            e.printStackTrace();
            response.getWriter().write("エラーが発生しました: " + e.getMessage());
        }
    }
}
