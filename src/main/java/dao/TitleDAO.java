package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Title;

/**
 * DBから情報を取得、登録を行うDAO
 * @author 馬場 健太朗
 */
public class TitleDAO {
    // DB接続情報
	
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
     * 部分一致のキーワードでタイトル一覧を検索するメソッド
     * 
     * @param keyword 検索するキーワード
     * @return 検索結果のタイトルリスト
     * @throws SQLException SQLの実行時例外が発生した場合
     */
    public List<Title> searchTitles(String keyword) throws SQLException {
        // DBに登録しているタイトルのリストのインスタンスを作成
        List<Title> titles = new ArrayList<>();
        // プレースホルダー
        // タイトル名の部分一致検索するsql
        String searchTitlesSql = "SELECT * FROM title WHERE title_name LIKE ?";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            PreparedStatement searchTitlesStmt = conn.prepareStatement(searchTitlesSql)) {

            // 入力文字keywordの部分一致検索させる文字をプレースホルダーにセット
            searchTitlesStmt.setString(1, "%" + keyword + "%");
            //実行結果を格納
            ResultSet resultSet = searchTitlesStmt.executeQuery();

            // 空白行に行くまで繰り返し
            while (resultSet.next()) {
            	// タイトルIDを取得
                int titleId = resultSet.getInt("title_id");
                //タイトル名を取得
                String titleName = resultSet.getString("title_name");
                // TitleインスタンスにIDとタイトル名を格納
                titles.add(new Title(titleId, titleName));
            }
        }
        return titles;
    }

    /**
     * DBにタイトルを登録するメソッド
     * @param titleName 登録するタイトルの名前
     * @return 登録が成功した場合は true, 既に同名のタイトルが存在する場合は false
     */
    public boolean registerTitle(String titleName) {
        // プレースホルダー
        // タイトル名を検索してタイトルIDを表示するSQL文
        String searchTitleSql = "SELECT title_id FROM title WHERE title_name = ?";
        // タイトル名を登録するSQL文
        String insertTitleSql = "INSERT INTO title (title_name) VALUES (?)";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            // タイトル検索のPreparedStatement
            PreparedStatement searchTitleStmt = conn.prepareStatement(searchTitleSql);
            // タイトルを登録するPreparedStatement
            PreparedStatement insertTitleStmt = conn.prepareStatement(insertTitleSql)) {
            // searchTitleでタイトル名を検索するのでプレースホルダーにセット
            searchTitleStmt.setString(1, titleName);
            // 検索結果をresultSetに代入
            ResultSet resultSet = searchTitleStmt.executeQuery();

            // タイトルが存在しない場合のみ登録
            if (!resultSet.next()) {
                // 登録するタイトル名をセット
                insertTitleStmt.setString(1, titleName);
                // タイトル名の登録処理の実行
                insertTitleStmt.executeUpdate();
                // 登録が成功した場合はtrueを返す
                return true;
            } else {
                // タイトルが既に存在する場合はfalseを返す
                return false;
            }
        } catch (SQLException e) {
            // タイトルの登録中にエラーが発生しました。
            throw new RuntimeException("タイトルの登録中にエラーが発生しました。", e);
        }
    }

    /**
     * DBにタイトルIDからキャラクターを登録するメソッド
     * @param characterName キャラクターの名前
     * @param titleId       タイトルのID
     * @return 登録が成功した場合は true, 既に同名のキャラクターが存在する場合は false
     */
    public boolean registerCharacter(String characterName, int titleId) {
        // プレースホルダー
        // そのタイトルidに同名のキャラクターがいるか確認するSQL文
        String searchCharacterSql = "SELECT character_id FROM character WHERE title_id = ? AND character_name = ?";
        // いない場合、そのタイトルIDにキャラクターを追加するSQL文
        String insertCharacterSql = "INSERT INTO character (character_name, title_id) VALUES (?, ?)";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            // キャラクターが登録済みか確認するPreparedStatement
            PreparedStatement searchCharacterStmt = conn.prepareStatement(searchCharacterSql);
            // 存在しない場合に追加するPreparedStatement
            PreparedStatement insertCharacterStmt = conn.prepareStatement(insertCharacterSql)) {

            // searchCharacterのプレースホルダーにセット
            searchCharacterStmt.setInt(1, titleId);
            searchCharacterStmt.setString(2, characterName);

            // キャラクターが存在しているか確認するクエリを実行
            ResultSet resultSet = searchCharacterStmt.executeQuery();

            // もしresultSet内の結果が空ならtrue
            if (!resultSet.next()) {
                // insertCharacterのプレースホルダーにセット
                insertCharacterStmt.setString(1, characterName);
                insertCharacterStmt.setInt(2, titleId);
                // insertCharacterSQLを実行して登録
                insertCharacterStmt.executeUpdate();
                // 登録が成功した場合は true を返す
                return true;
            } else {
                // キャラクターが既に存在する場合は false を返す
                return false;
            }
        } catch (SQLException e) {
            // キャラクターの登録中にエラーが発生しました。
            throw new RuntimeException("キャラクターの登録中にエラーが発生しました。", e);
        }
    }

    /**
     * タイトルが既に存在するか確認するメソッド
     * @param titleName 確認するタイトルの名前
     * @return タイトルが既に存在する場合は true, それ以外は false
     */
    public boolean seachTitle(String titleName) {
        // タイトル名を検索してタイトルが存在するか確認するSQL文
        String searchTitleSql = "SELECT title_id FROM title WHERE title_name = ?";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            PreparedStatement searchTitleStmt = conn.prepareStatement(searchTitleSql)) {

            // プレースホルダーにタイトル名をセット
            searchTitleStmt.setString(1, titleName);
            // 実行結果を格納
            ResultSet resultSet = searchTitleStmt.executeQuery();

            // タイトルが存在する場合は true を返す
            return resultSet.next();
        } catch (SQLException e) {
            // エラーが発生した場合は実行時例外をスロー
            throw new RuntimeException("タイトル検索中にエラーが出ました", e);
        }
    }
    
    /**
     * タイトル名からタイトルIDを取得するメソッド
     * @param titleName タイトル名
     * @return タイトルID
     */
    public int getTitleId(String titleName) {
        // プレースホルダー
        // タイトル名からタイトルIDを検索するSQL文
        String searchTitleIdSql = "SELECT title_id FROM title WHERE title_name = ?";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            PreparedStatement searchTitleIdStmt = conn.prepareStatement(searchTitleIdSql)) {

            // プレースホルダーにタイトル名をセット
            searchTitleIdStmt.setString(1, titleName);
            //実行結果を格納
            ResultSet resultSet = searchTitleIdStmt.executeQuery();

            // 空白行に行くまで繰り返し
            if (resultSet.next()) {
                // 検索したタイトルIDを取得
                int titleId = resultSet.getInt("title_id");
                // タイトルIDが存在するかどうかを確認
                if (!resultSet.wasNull()) {
                    // 検索結果のtitleIdを返す
                    return titleId;
                } else {
                    // タイトルIDが無効です。
                    throw new SQLException("タイトルIDが無効です。");
                }
            } else {
                // このタイトルは見つかりません: titleName
                throw new SQLException("このタイトルは見つかりません: " + titleName);
            }
        } catch (SQLException e) {
            // タイトルIDの取得に失敗しました
            throw new RuntimeException("タイトルIDの取得に失敗しました", e);
        }
    }

    /**
     * DBから全タイトルを検索するメソッド
     * @return 全タイトルのリスト
     */
    public List<String> getAllTitles() {
        // タイトル名のリストを格納するためのArrayListを作成
        List<String> titleList = new ArrayList<>();
        // 全タイトルを取得するSQL文
        String searchAllTitlesSql = "SELECT title_name FROM title";

        try (
            // 接続処理
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            PreparedStatement searchAllTitlesStmt = conn.prepareStatement(searchAllTitlesSql);
        	//実行結果を格納
            ResultSet resultSet = searchAllTitlesStmt.executeQuery();) {
        	// 空白行に行くまで繰り返し
            while (resultSet.next()) {
                String titleName = resultSet.getString("title_name");
                titleList.add(titleName);
            }
        } catch (SQLException e) {
            // エラーが発生した場合は実行時例外をスロー
            throw new RuntimeException("全タイトルの取得中にエラーが発生しました。", e);
        }
        // 最終的なタイトル名のリストを返す
        return titleList;
    }

    /**
     * DBから特定のタイトルに関連するキャラクター一覧を取得するメソッド
     * @param titleId タイトルID
     * @return キャラクター名のリスト
     * @throws RuntimeException キャラクター一覧の取得中にエラーが発生した場合
     */
    public List<String> getCharacters(int titleId) {
        // タイトルIDからキャラクター一覧を取得するSQL文
        String searchCharactersSql = "SELECT character_name FROM character WHERE title_id = ?";

        try (
            // データベースへの接続
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            // SQL文を実行するためのPreparedStatementの生成
            PreparedStatement searchCharactersStmt = conn.prepareStatement(searchCharactersSql)) {

            // プレースホルダーにタイトルIDをセット
            searchCharactersStmt.setInt(1, titleId);
            //実行結果を格納
            ResultSet resultSet = searchCharactersStmt.executeQuery();

            // キャラクター名のリストを格納するArrayList
            List<String> characters = new ArrayList<>();

            // 空白行に行くまで繰り返し
            while (resultSet.next()) {
                // 検索結果をcharacterNameに格納
                String characterName = resultSet.getString("character_name");
                // キャラクター名をリストに追加
                characters.add(characterName);
            }
            // キャラクターの一覧を返す
            return characters;
        } catch (SQLException e) {
            // エラーが発生した場合は実行時例外をスロー
            throw new RuntimeException("キャラクター一覧の取得中にエラーが発生しました", e);
        }
    }
}
