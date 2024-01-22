package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRepository {
    public static HikariDataSource dataSource;
}
