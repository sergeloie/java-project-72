package hexlet.code.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NonNull;

@Data
public class Url {
    private Long id;
    @NonNull private String name;
    @NonNull private Timestamp createdAt;
}
