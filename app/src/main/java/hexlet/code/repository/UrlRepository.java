package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UrlRepository extends BaseRepository {


    public static void save(Url url) throws SQLException {

        String sql = "INSERT INTO urls (NAME, CREATED_AT) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.setTimestamp(2, url.getCreatedAt());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an ID after saving an entity");
            }
        }
    }

    public static List<Url> getEntities() throws SQLException {
        String sql = "SELECT * FROM urls";
        List<Url> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                Timestamp timestamp = resultSet.getTimestamp("CREATED_AT");
                Url url = new Url(name, timestamp);
                url.setId(id);
                result.add(url);
            }
        }
        return result;
    }

    public static Optional<Url> find(Long id) throws SQLException {
        String sql = "SELECT * FROM urls WHERE ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                Timestamp timestamp = resultSet.getTimestamp("CREATED_AT");
                Url url = new Url(name, timestamp);
                url.setId(id);
                return Optional.of(url);
            }
        }
        return Optional.empty();
    }

    public static Optional<Url> find(String name) throws SQLException {
        String sql = "SELECT * FROM urls WHERE NAME = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("ID");
                Timestamp timestamp = resultSet.getTimestamp("CREATED_AT");
                Url url = new Url(name, timestamp);
                url.setId(id);
                return Optional.of(url);
            }
        }
        return Optional.empty();
    }
}
