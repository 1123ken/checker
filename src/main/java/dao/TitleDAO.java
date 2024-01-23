package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Title;

public class TitleDAO {
    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/yogaShiten";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "admin";

    public List<Title> searchTitles(String keyword) throws SQLException {
        List<Title> titles = new ArrayList<>();
        String sql = "SELECT * FROM titles WHERE title LIKE ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setString(1, "%" + keyword + "%");
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                int titleId = resultSet.getInt("title_id");
                String title = resultSet.getString("title");

                Title t = new Title(titleId, title);
                titles.add(t);
            }
        }
        return titles;
    }
}
