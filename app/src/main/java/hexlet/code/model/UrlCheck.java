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
    Long id;
    @NonNull Long statusCode;
    @NonNull String title;
    @NonNull String h1;
    @NonNull String description;
    @NonNull Long urlId;
    @NonNull Timestamp createdAt;


}
