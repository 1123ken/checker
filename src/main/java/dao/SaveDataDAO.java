package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveDataDAO {
	
	private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/checker";
	private final static String DB_USER = "postgres";
	private final static String DB_PASS = "admin";
	

    private final String INSERT_DATA_SQL = "INSERT INTO point (title_id, my_character_id, your_character_id,point, cpoint) VALUES (?, ?, ?, ?, ?)";

    public void saveData(int titleId, int myCharacterId, int yourCharacterId,  String point, String cpoint) {
        try (
        		
        		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
            try (PreparedStatement statement = conn.prepareStatement(INSERT_DATA_SQL)) {
                statement.setInt(1, titleId);
                statement.setInt(2, myCharacterId);
                statement.setInt(3, yourCharacterId);
                statement.setString(4, point);
                statement.setString(5, cpoint);

                // データベースに挿入
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
