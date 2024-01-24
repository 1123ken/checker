package dao;

//DBから情報を取得、登録を行うDAO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Title;

public class TitleDAO {
    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "admin";

    //DBから登録されているタイトルを検索するメソッド
    public List<Title> searchTitles(String keyword) throws SQLException {
    	
        // DB に登録しているタイトルのリストのインスタンスを作成
        List<Title> titles = new ArrayList<>();
        
        // プレースホルダー
        String sql = "SELECT * FROM title WHERE title_name LIKE ?";

        try (
                // 接続情報の代入
                Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
        		
                // プリペアードステートメントの作成
                PreparedStatement pStmt = conn.prepareStatement(sql)) {

            // 部分一致検索
            pStmt.setString(1, "%" + keyword + "%");
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                int titleId = resultSet.getInt("title_id");
                String titleName = resultSet.getString("title_name");

                Title game = new Title(titleId, titleName);
                titles.add(game);
            }
        }
        return titles;
    }

    //DBにタイトルを登録するメソッド
    public boolean registerTitle(String titleName) {
        String selectTitleSql = "SELECT title_id FROM title WHERE title_name = ?";
        String insertTitleSql = "INSERT INTO title (title_name) VALUES (?)";

        try (
        		//接続処理
                Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
                PreparedStatement selectTitleStmt = conn.prepareStatement(selectTitleSql);
                PreparedStatement insertTitleStmt = conn.prepareStatement(insertTitleSql)) {

            // タイトルが既に存在するかチェック
            selectTitleStmt.setString(1, titleName);
            ResultSet resultSet = selectTitleStmt.executeQuery();

            if (!resultSet.next()) {
                // タイトルが存在しない場合のみ登録
                insertTitleStmt.setString(1, titleName);
                insertTitleStmt.executeUpdate();
                return true; // 登録が成功した場合は true を返す
            } else {
                return false; // タイトルが既に存在する場合は false を返す
            }
        } catch (SQLException e) {
            throw new RuntimeException("タイトルの登録中にエラーが発生しました。", e);
        }
    }

    //DBにキャラクターを登録するメソッド
    public boolean registerCharacter(String characterName, int titleId) {
    	
    	//プレースホルダー
        String selectCharacterSql = "SELECT character_id FROM character WHERE title_id = ? AND character_name = ?";
        String insertCharacterSql = "INSERT INTO character (character_name, title_id) VALUES (?, ?)";

        try (
        		//接続処理
                Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
                PreparedStatement selectCharacterStmt = conn.prepareStatement(selectCharacterSql);
                PreparedStatement insertCharacterStmt = conn.prepareStatement(insertCharacterSql)) {

            // キャラクターが既に存在するかチェック
            selectCharacterStmt.setInt(1, titleId);
            selectCharacterStmt.setString(2, characterName);
            ResultSet resultSet = selectCharacterStmt.executeQuery();

            if (!resultSet.next()) {
                // キャラクターが存在しない場合のみ登録
                insertCharacterStmt.setString(1, characterName);
                insertCharacterStmt.setInt(2, titleId);
                insertCharacterStmt.executeUpdate();
                return true; // 登録が成功した場合は true を返す
            } else {
                return false; // キャラクターが既に存在する場合は false を返す
            }
        } catch (SQLException e) {
            throw new RuntimeException("キャラクターの登録中にエラーが発生しました。", e);
        }
    }
    
    //DBからタイトルIDを取得するメソッド
    public int getTitleId(String titleName) {
        String sql = "SELECT title_id FROM title WHERE title_name = ?";

        try (	//接続処理
        		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
        		PreparedStatement pStmt = conn.prepareStatement(sql)) {
            	pStmt.setString(1, titleName);
            	ResultSet resultSet = pStmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("title_id");
            } else {
                throw new SQLException("このタイトルは見つかりません" + titleName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting title ID.", e);
        }
    }
    
    
    //DBからキャラクター情報を取得するメソッド
    public List<String> getCharacters(int title_id) {
        String sql = "SELECT character_name FROM character WHERE title_id = ?";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement pStmt = conn.prepareStatement(sql)
        ) {
            pStmt.setInt(1, title_id);
            ResultSet resultSet = pStmt.executeQuery();

            List<String> characters = new ArrayList<>();

            while (resultSet.next()) {
                String characterName = resultSet.getString("character_name");
                characters.add(characterName);
            }

            return characters;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting characters.", e);
        }
    }
}
