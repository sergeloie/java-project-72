package hexlet.code.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@RequiredArgsConstructor
@Getter
@Setter
public class UrlCheck {
    Integer id;
    @NonNull Integer statusCode;
    @NonNull String title;
    @NonNull String h1;
    @NonNull String description;
    @NonNull Integer urlId;
    @NonNull Timestamp createdAt;


}
