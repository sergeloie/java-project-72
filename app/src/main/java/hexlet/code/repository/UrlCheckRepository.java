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
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks (STATUS_CODE, TITLE, H1, DESCRIPTION, URL_ID, CREATED_AT) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, urlCheck.getStatusCode());
            preparedStatement.setString(2, urlCheck.getTitle());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getDescription());
            preparedStatement.setInt(5, urlCheck.getUrlId());
            Instant instant = Instant.now();
            urlCheck.setCreatedAt(instant);
            preparedStatement.setTimestamp(6, Timestamp.from(instant));

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
        String sql = "SELECT * FROM url_checks WHERE URL_ID = ? ORDER BY id DESC";
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
                urlCheck.setCreatedAt(timestamp.toInstant());
                result.add(urlCheck);
            }
        }
        return result;
    }

    public static Map<Integer, UrlCheck> findLatestCheck() throws SQLException {
        String sql = "SELECT DISTINCT ON (url_id) * from url_checks order by url_id DESC, id DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            HashMap<Integer, UrlCheck> result = new HashMap<Integer, UrlCheck>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int urlId = resultSet.getInt("URL_ID");
                int statusCode = resultSet.getInt("STATUS_CODE");
                String title = resultSet.getString("TITLE");
                String h1 = resultSet.getString("H1");
                String description = resultSet.getString("DESCRIPTION");
                Instant instant = resultSet.getTimestamp("CREATED_AT").toInstant();
                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
                urlCheck.setId(id);
                urlCheck.setUrlId(urlId);
                urlCheck.setCreatedAt(instant);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }
}
