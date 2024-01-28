package hexlet.code.controller;

import hexlet.code.dto.url.BuildUrlPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collections;

public class UrlController implements CrudHandler {
    /**
     * @param context
     */
    @Override
    public void create(@NotNull Context context) {
        String string = context.formParam("url");
        try {
            URI uri = new URI(string);
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
            context.sessionAttribute("flash", "Некорректный URL");
            context.sessionAttribute("flashType", "success");
            BuildUrlPage page = new BuildUrlPage(string, null);
            context.render("index.jte", Collections.singletonMap("page", page));
        }

        try {
            Url url = UrlRepository.find(string).get();
            if (url != null) {

            }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




    /**
     * @param context
     * @param s
     */
    @Override
    public void delete(@NotNull Context context, @NotNull String s) {

    }

    /**
     * @param context
     */
    @Override
    public void getAll(@NotNull Context context) {

    }

    /**
     * @param context
     * @param s
     */
    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {

    }

    /**
     * @param context
     * @param s
     */
    @Override
    public void update(@NotNull Context context, @NotNull String s) {

    }
}
