package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.GameCharacter;

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
	        	Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);{
	    			String sql = "SELECT character_id FROM character WHERE character_name = ?";
	    			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
	    				pStmt.setString(1, characterName);
	    				try (ResultSet resultSet = pStmt.executeQuery()) {
	    					if (resultSet.next()) {
	    						characterId = resultSet.getInt("character_id");
	    					}
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // エラー処理は適切に行ってください
	        }
	        return characterId;
	    }
}
