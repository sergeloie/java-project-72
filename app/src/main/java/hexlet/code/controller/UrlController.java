/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.controller;

import lombok.extern.slf4j.Slf4j;

import hexlet.code.dto.url.UrlPage;
import hexlet.code.dto.url.UrlsPage;
import hexlet.code.dto.urlcheck.UrlChecksPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.Utilities;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static hexlet.code.util.NamedRoutes.ROOT_PATH;

@Slf4j
public class UrlController {

    /**
     * @param context
     */


    public static void create(Context context) {
        String string = context.formParam("url");
        URI uri;

        try {
            uri = new URL(string).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.sessionAttribute("flashType", "danger");
            context.redirect(ROOT_PATH);
            return;
        }

        String name = Utilities.uriToString(uri);
        try {
            if (UrlRepository.find(name).isPresent()) {
                context.sessionAttribute("flash", "Страница уже существует");
                context.sessionAttribute("flashType", "danger");
                context.redirect(ROOT_PATH);
                return;
            }
        } catch (SQLException e) {
            log.error("Error while checking Url isPresent in DB");
        }

        Url urlToSave = new Url(name);
        try {
            UrlRepository.save(urlToSave);
        } catch (SQLException e) {
            log.error("Error while saving Url to DB");
        }
        context.sessionAttribute("flash", "Страница успешно добавлена");
        context.sessionAttribute("flashType", "success");
        context.redirect(ROOT_PATH);


    }


    /**
     * @param context
     */


    public static void getAll(Context context) {
        List<Url> urls = null;
        try {
            urls = UrlRepository.getEntities();
        } catch (SQLException e) {

            log.error("Error when retrieving all Urls");
        }
        UrlsPage page = new UrlsPage(urls);
        context.render("url/index.jte", Collections.singletonMap("page", page));

    }

    /**
     * @param context
     */

    public static void getOne(Context context) {
        int id = context.pathParamAsClass("id", Integer.class).get();
        Url url = null;
        List<UrlCheck> urlChecks = new ArrayList<>();
        try {
            url = UrlRepository.find(id).orElseThrow(() -> new NotFoundResponse("Url with ID: " + id + " not found"));
        } catch (SQLException e) {
            log.error("Error when retrieving the Url");
        }

        try {
            urlChecks = UrlCheckRepository.getEntities(id);
        } catch (SQLException e) {
            log.error("Error when retrieving all Urls");
        }
        UrlPage page = new UrlPage(url);
        UrlChecksPage urlChecksPage = new UrlChecksPage(urlChecks);
        page.setFlash(context.consumeSessionAttribute("flash"));
        page.setFlashType(context.consumeSessionAttribute("flashType"));
        context.render("url/show.jte", Map.of("page", page, "urlChecksPage", urlChecksPage));
    }
}
