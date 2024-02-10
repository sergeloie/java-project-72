/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_check (STATUS_CODE, TITLE, H1, DESCRIPTION, URL_ID, CREATED_AT) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, urlCheck.getStatusCode());
            preparedStatement.setString(2, urlCheck.getTitle());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getDescription());
            preparedStatement.setInt(5, urlCheck.getUrlId());
            preparedStatement.setTimestamp(6, urlCheck.getCreatedAt());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("DB have not returned an ID after saving an entity");
            }
        }
    }

    public static List<UrlCheck> getEntities(int urlId) throws SQLException {
        String sql = "SELECT * FROM url_check WHERE URL_ID = ?";
        List<UrlCheck> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, urlId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int statusCode = resultSet.getInt("STATUS_CODE");
                String title = resultSet.getString("TITLE");
                String h1 = resultSet.getString("H1");
                String description = resultSet.getString("DESCRIPTION");
                Timestamp timestamp = resultSet.getTimestamp("CREATED_AT");
                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
                urlCheck.setId(id);
                urlCheck.setUrlId(urlId);
                urlCheck.setCreatedAt(timestamp);
                result.add(urlCheck);
            }
        }
        return result;
    }


}
