/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;

import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UrlPage extends BasePage {
    private Url url;
    private List<UrlCheck> urlChecks;
}
