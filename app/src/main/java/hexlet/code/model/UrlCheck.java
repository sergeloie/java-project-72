/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;


@RequiredArgsConstructor
@Getter

public final class UrlCheck {
    public void setId(Integer id) {
        this.id = id;
    }

    Integer id;
    @NonNull Integer statusCode;
    @NonNull String title;
    @NonNull String h1;
    @NonNull String description;
    @NonNull Integer urlId;
    @NonNull Timestamp createdAt;


}
