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

public class CharacterDAO {

	private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
	private final static String DB_USER = "postgres";
	private final static String DB_PASS = "admin";

	Connection conn = null;

	//characterテーブルからキャラクターを名を取得するメソッド
	public List<GameCharacter> getAllCharacters() {

		//キャラクターリストインスタンスの生成
		List<GameCharacter> characters = new ArrayList<>();

		try (
				//DB接続処理
				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//プレースホルダー
			String sql = "SELECT * FROM character WHERE title_id = ?";

			try (
					//sql文を当て込む
					PreparedStatement pStmt = conn.prepareStatement(sql)) {

				try (
						ResultSet resultSet = pStmt.executeQuery()) {

					while (resultSet.next()) {
						int characterId = resultSet.getInt("characterId");
						String characterName = resultSet.getString("characterName");
						characters.add(new GameCharacter(characterId, characterName));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return characters;
	}

	// 特定のタイトルに紐づくキャラクターのリストを取得
	public List<GameCharacter> getAllCharactersForTitle(int titleId) {
		List<GameCharacter> characters = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM character WHERE title_id = ?";
			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				pStmt.setInt(1, titleId);
				try (ResultSet resultSet = pStmt.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("character_id");
						String name = resultSet.getString("character_name");
						characters.add(new GameCharacter(id, name));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return characters;
	}

	//キャラクターIDの取得
	public static int getCharacterIdByName(String characterName) {
		int characterId = -1; // エラー時のデフォルト値
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			{
				String sql = "SELECT character_id FROM character WHERE character_name = ?";
				try (
						PreparedStatement pStmt = conn.prepareStatement(sql)) {
					pStmt.setString(1, characterName);
					try (ResultSet resultSet = pStmt.executeQuery()) {
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

	//自操作キャラクター名からpointテーブルの情報を引っ張ってくるメソッド
	public List<Beans> getAllCharacterData(String myCharacter, String yourCharacter) {
		//宣言＋初期化処理
		List<Beans> bl = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			//接続処理
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//myCharacterとyourCharacterからpointテーブルの内容を検索
			String sql = "SELECT title_id, your_character_id, point, cpoint FROM point WHERE my_character_id = (SELECT character_id FROM character WHERE character_name = ?) AND your_character_id = (SELECT character_id FROM character WHERE character_name = ?)";

			pstmt = conn.prepareStatement(sql);

			//プレースホルダーにセット
			pstmt.setString(1, myCharacter);
			pstmt.setString(2, yourCharacter);

			//resultSetに検索結果の格納
			resultSet = pstmt.executeQuery();

			//空白行まで取得
			while (resultSet.next()) {
				String title_id = resultSet.getString("title_id");
				String yourCharacterFromDB = resultSet.getString("your_character_id");
				String point = resultSet.getString("point");
				String cpoint = resultSet.getString("cpoint");

				//beansインスタンスに保管
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