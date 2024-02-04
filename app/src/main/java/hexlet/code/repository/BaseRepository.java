package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;

public class BaseRepository {
    private BaseRepository() {
        throw new IllegalStateException("Utility class");
    }
    public static HikariDataSource dataSource;
}
