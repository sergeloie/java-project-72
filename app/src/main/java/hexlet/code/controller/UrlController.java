package hexlet.code.controller;

import hexlet.code.dto.url.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.Utilities;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;

public class UrlController implements CrudHandler {
    /**
     * @param context
     */

    @Override
    public void create(@NotNull Context context) {
        String string = context.formParam("url");

/*        if (Utilities.isValidUrl(string)) {
            URI uri = new URI(string);
            String name = Utilities.uriToString(uri);
            if (UrlRepository.find(name).isPresent()) {
                context.sessionAttribute("flash", "Страница уже существует");
                context.sessionAttribute("flashType", "alert");
                context.redirect(NamedRoutes.ROOT_PATH);
            }
            Url urlToSave = new Url(name, Timestamp.valueOf(LocalDateTime.now()));
            UrlRepository.save(urlToSave);
            context.sessionAttribute("flash", "Страница успешно добавлена");
            context.sessionAttribute("flashType", "success");
            context.redirect(NamedRoutes.ROOT_PATH);
        } else {
            context.sessionAttribute("flash", "Некорректный URL");
            context.sessionAttribute("flashType", "alert");
            context.redirect(NamedRoutes.ROOT_PATH);

        }*/
        try {
            URI uri = new URL(string).toURI();
            String name = Utilities.uriToString(uri);
            if (UrlRepository.find(name).isPresent()) {
                context.sessionAttribute("flash", "Страница уже существует");
                context.sessionAttribute("flashType", "danger");
                context.redirect(NamedRoutes.ROOT_PATH);
            }
            Url urlToSave = new Url(name, Timestamp.valueOf(LocalDateTime.now()));
            UrlRepository.save(urlToSave);
            context.sessionAttribute("flash", "Страница успешно добавлена");
            context.sessionAttribute("flashType", "success");
            context.redirect(NamedRoutes.ROOT_PATH);
        } catch (URISyntaxException | MalformedURLException e) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.sessionAttribute("flashType", "danger");
            context.redirect(NamedRoutes.ROOT_PATH);
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
    @SneakyThrows
    @Override
    public void getAll(@NotNull Context context) {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        context.render("url/index.jte", Collections.singletonMap("page", page));

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
