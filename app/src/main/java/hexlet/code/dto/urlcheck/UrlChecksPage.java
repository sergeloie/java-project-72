package hexlet.code.dto.urlcheck;

import hexlet.code.dto.BasePage;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class UrlChecksPage extends BasePage {
    private List<UrlCheck> urlChecks;
}
