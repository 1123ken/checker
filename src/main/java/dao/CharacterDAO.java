package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Beans;
import model.GameCharacter;

/**
 * キャラクターに関する登録、取得するDAOクラス
 * @author 馬場健太朗
 */
public class CharacterDAO {

	// PostgreSQLに接続するための情報
	private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
	private final static String DB_USER = "postgres";
	private final static String DB_PASS = "admin";

	// Connectionの宣言、初期化
	Connection conn = null;

	/**
	 * characterテーブルから全てのキャラクター名を取得するメソッドです。
	 * @return タイトルに関連する全キャラクターの一覧
	 */
	public List<GameCharacter> getAllCharacters() {
		// キャラクターリストインスタンスの生成
		List<GameCharacter> characters = new ArrayList<>();

		try (
			// 接続処理
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// プレースホルダー
			// タイトルidからそのタイトルのキャラクターすべてを取得する
			String allCharacterSql = "SELECT * FROM character WHERE title_id = ?";

			// SQL文を実行するためのPreparedStatementの生成
			PreparedStatement allCharacterStmt = conn.prepareStatement(allCharacterSql);
			//実行結果を格納
			ResultSet resultSet = allCharacterStmt.executeQuery();

			// 空白行になるまで繰り返し
			while (resultSet.next()) {
				// キャラクターのIDを取得
				int characterId = resultSet.getInt("characterId");
				// キャラクター名を取得
				String characterName = resultSet.getString("characterName");
				// 取得したIDと名前をcharactersに格納
				characters.add(new GameCharacter(characterId, characterName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 取得したキャラクターリストを返す
		return characters;
	}

	/**
	 * 特定のタイトルに紐づくキャラクターのリストを取得
	 * 
	 * @param titleId タイトルID
	 * @return 
	 */
	public List<GameCharacter> getAllCharactersForTitle(int titleId) {
		List<GameCharacter> characters = new ArrayList<>();

		try (
				// 接続処理
				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// プレースホルダー
			String sql = "SELECT * FROM character WHERE title_id = ?";

			try (
					// SQL文を当て込む
					PreparedStatement pStmt = conn.prepareStatement(sql)) {

				// プレースホルダーにセット
				pStmt.setInt(1, titleId);
				try (
						// 結果セット
						ResultSet resultSet = pStmt.executeQuery()) {

					// 空白行に登録(add)
					while (resultSet.next()) {
						// キャラクターのID
						int id = resultSet.getInt("character_id");
						// キャラクターの名前
						String name = resultSet.getString("character_name");
						// charactersリストに新しいキャラクターを追加
						characters.add(new GameCharacter(id, name));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return characters;
	}

	/**
	 * キャラクター名からキャラクターIDを取得するメソッド
	 * Retrieves the character ID from the character name.
	 * 
	 * @param characterName キャラクター名
	 * @return Character ID
	 */
	public static int getCharacterIdByName(String characterName) {
		// エラー時のデフォルト値
		int characterId = -1;

		try {
			// 接続処理
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			{
				// プレースホルダー
				String sql = "SELECT character_id FROM character WHERE character_name = ?";
				try (
						// pStmtを作成して
						PreparedStatement pStmt = conn.prepareStatement(sql)) {
					// プレースホルダーにセット	
					pStmt.setString(1, characterName);
					try (
							// クエリ結果をresultSetに格納
							ResultSet resultSet = pStmt.executeQuery()) {
						// 空白行に取得
						if (resultSet.next()) {
							characterId = resultSet.getInt("character_id");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return characterId;
	}

	/**
	 * 自分のキャラクター名と相手のキャラクター名からpointテーブルの情報を取得するメソッド
	 * Retrieves information from the point table based on the player's and opponent's character names.
	 * 
	 * @param myCharacter 自分のキャラクター名
	 * @param yourCharacter 相手のキャラクター名
	 * @return List of Beans instances
	 */
	public List<Beans> getAllCharacterData(String myCharacter, String yourCharacter) {

		List<Beans> bl = new ArrayList<>();

		// 初期化
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// 接続処理
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// myCharacterとyourCharacterからpointテーブルの内容を検索
			String sql = "SELECT title_id, your_character_id, point, cpoint \r\n"
					+ "FROM point \r\n"
					+ "WHERE my_character_id IN (SELECT character_id FROM character WHERE character_name = ?) \r\n"
					+ "AND your_character_id IN (SELECT character_id FROM character WHERE character_name = ?);\r\n"
					+ "";

			pstmt = conn.prepareStatement(sql);

			// プレースホルダーにセット
			pstmt.setString(1, myCharacter);
			pstmt.setString(2, yourCharacter);

			// resultSetに検索結果の格納
			resultSet = pstmt.executeQuery();

			// 空白行に値を入れる(add)
			while (resultSet.next()) {
				String title_id = resultSet.getString("title_id");
				String yourCharacterFromDB = resultSet.getString("your_character_id");
				String point = resultSet.getString("point");
				String cpoint = resultSet.getString("cpoint");

				// beansインスタンスに保管
				Beans beans = new Beans(title_id, myCharacter, yourCharacterFromDB, point, cpoint);
				bl.add(beans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// クローズ処理を実行
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bl;
	}
}
