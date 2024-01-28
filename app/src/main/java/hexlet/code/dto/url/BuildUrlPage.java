package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildUrlPage extends BasePage {

    private String string;
    private Map<String, List<ValidationError<Object>>> errors;
}
