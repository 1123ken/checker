package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * pointテーブルにデータを保存するDAO
 * @author 馬場 健太朗
 */
public class SaveDataDAO {
    /** データベース接続情報 */
	
	/**
	 * postgressのパス
	 */
	private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
	
	/**
	 * ユーザー名
	 */
	private final static String DB_USER = "postgres";
	
	/**
	 * パスワード
	 */
	private final static String DB_PASS = "admin";

    /**
     * pointテーブルに入力した情報を登録するメソッド
     * @param titleId タイトルID
     * @param myCharacterId 自キャラクターID
     * @param yourCharacterId 対策キャラクターID
     * @param point きつい所情報
     * @param cpoint 対策情報
     */
    public void saveData(int titleId, int myCharacterId, int yourCharacterId, String point, String cpoint) {
        /** プレースホルダー */
        String insertSaveDataSql = "INSERT INTO point (title_id, my_character_id, your_character_id,point, cpoint) VALUES (?, ?, ?, ?, ?)";
        try (
                /** 接続処理 */
                Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
            try (
                // SQL文を実行するためのPreparedStatementの生成
                //pointテーブルに保存するPreparedStatement
                PreparedStatement insertSaveDataStmt = conn.prepareStatement(insertSaveDataSql)) {
                
                //プレースホルダーに保存する各情報をセット
                insertSaveDataStmt.setInt(1, titleId);
                insertSaveDataStmt.setInt(2, myCharacterId);
                insertSaveDataStmt.setInt(3, yourCharacterId);
                insertSaveDataStmt.setString(4, point);
                insertSaveDataStmt.setString(5, cpoint);
                //pointテーブルへの登録処理を実行
                insertSaveDataStmt.executeUpdate();
            }
        }  catch (SQLException e) {
            throw new RuntimeException("pointテーブルへのデータ登録中にエラーが発生しました。", e);
        }
    }
}
