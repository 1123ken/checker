package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Title;

public class TitleDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "admin";
	
	Connection conn = null;
	
	//タイトル検索用のメソッド
	public List<Title> searchTitles(String keyword) throws SQLException {

		//DBに登録しているタイトルのリストのインスタンスを作成
		List<Title> titles = new ArrayList<>();

		//プレースホルダー
		String sql = "SELECT * FROM title WHERE title LIKE ?";
		
		try (
				//接続情報の代入
				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//	
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			//部分一致検索
			pStmt.setString(1, "%" + keyword + "%");
			ResultSet resultSet = pStmt.executeQuery();

			while (resultSet.next()) {
				int titleId = resultSet.getInt("title_id");
				String title = resultSet.getString("title");

				Title game = new Title(titleId, title);
				titles.add(game);
			}
		}
		return titles;
	}

	public boolean registerOrUpdateTitleAndCharacter(String title, String character) {
		String insertTitleSql = "INSERT INTO title (title) VALUES (?)";
		String insertCharacterSql = "INSERT INTO character (character_name) VALUES (?)";
		String selectTitleIdSql = "SELECT title_id FROM title WHERE title = ?";
		String selectCharacterIdSql = "SELECT character_id FROM character WHERE character_name = ?";
		String insertTitleCharacterSql = "INSERT INTO title_character (title_id, character_id) VALUES (?, ?)";
		
		try (
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			conn.setAutoCommit(false);

			// タイトルが存在するかチェック
			int titleId;
			try (PreparedStatement selectTitleIdStmt = conn.prepareStatement(selectTitleIdSql)) {
				selectTitleIdStmt.setString(1, title);
				ResultSet resultSet = selectTitleIdStmt.executeQuery();

				if (resultSet.next()) {
					// タイトルが存在する場合
					titleId = resultSet.getInt("title_id");
				} else {
					// タイトルが存在しない場合、タイトルを登録
					try (PreparedStatement insertTitleStmt = conn.prepareStatement(insertTitleSql,
							Statement.RETURN_GENERATED_KEYS)) {
						insertTitleStmt.setString(1, title);
						insertTitleStmt.executeUpdate();

						ResultSet generatedKeys = insertTitleStmt.getGeneratedKeys();
						if (generatedKeys.next()) {
							titleId = generatedKeys.getInt(1);
						} else {
							throw new SQLException("Failed to get generated keys for title.");
						}
					}
				}
			}

			// キャラクターが存在するかチェック
			int characterId;
			try (PreparedStatement selectCharacterIdStmt = conn.prepareStatement(selectCharacterIdSql)) {
				selectCharacterIdStmt.setString(1, character);
				ResultSet resultSet = selectCharacterIdStmt.executeQuery();

				if (resultSet.next()) {
					// キャラクターが存在する場合
					characterId = resultSet.getInt("character_id");
				} else {
					// キャラクターが存在しない場合、キャラクターを登録
					try (PreparedStatement insertCharacterStmt = conn.prepareStatement(insertCharacterSql,
							Statement.RETURN_GENERATED_KEYS)) {
						insertCharacterStmt.setString(1, character);
						insertCharacterStmt.executeUpdate();

						ResultSet generatedKeys = insertCharacterStmt.getGeneratedKeys();
						if (generatedKeys.next()) {
							characterId = generatedKeys.getInt(1);
						} else {
							throw new SQLException("Failed to get generated keys for character.");
						}
					}
				}
			}

			// タイトルとキャラクターの紐づけを行う
			try (PreparedStatement insertTitleCharacterStmt = conn.prepareStatement(insertTitleCharacterSql)) {
				insertTitleCharacterStmt.setInt(1, titleId);
				insertTitleCharacterStmt.setInt(2, characterId);
				insertTitleCharacterStmt.executeUpdate();
			}

			conn.commit(); // トランザクションのコミット

			return true; // トランザクション内で成功した場合のみ return
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback(); // トランザクションのロールバック
				}
			} catch (SQLException rollbackException) {
				// ロールバック時の例外が発生した場合のハンドリング
				rollbackException.printStackTrace();
			}
			throw new RuntimeException("トランザクションの処理中にエラーが発生しました。", e);
		} finally {
			try {
				if (conn != null) {
					conn.close(); // クローズ処理
				}
			} catch (SQLException closeException) {
				// クローズ時の例外が発生した場合のハンドリング
				closeException.printStackTrace();
			}
		}
	}
}