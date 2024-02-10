/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Url {
    private Integer id;
    @NonNull private String name;
    private Timestamp createdAt;
}
