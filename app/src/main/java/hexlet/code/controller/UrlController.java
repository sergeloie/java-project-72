/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static hexlet.code.util.NamedRoutes.ROOT_PATH;
import static hexlet.code.util.ResourceRoutes.FLASH;
import static hexlet.code.util.ResourceRoutes.FLASH_TYPE;
import static hexlet.code.util.ResourceRoutes.INCORRECT_URL;
import static hexlet.code.util.ResourceRoutes.PAGE_ADDED;
import static hexlet.code.util.ResourceRoutes.PAGE_EXIST;

public class UrlController implements CrudHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    /**
     * @param context
     */

    @Override
    public void create(@NotNull Context context) {
        String string = context.formParam("url");
        try {
            URI uri = new URL(string).toURI();
            String name = Utilities.uriToString(uri);
            if (UrlRepository.find(name).isPresent()) {
                context.sessionAttribute(FLASH, PAGE_EXIST);
                context.sessionAttribute(FLASH_TYPE, "danger");
                context.redirect(ROOT_PATH);
                return;
            }
            Url urlToSave = new Url(name, Timestamp.valueOf(LocalDateTime.now()));
            UrlRepository.save(urlToSave);
            context.sessionAttribute(FLASH, PAGE_ADDED);
            context.sessionAttribute(FLASH_TYPE, "success");
            context.redirect(ROOT_PATH);
        } catch (URISyntaxException | MalformedURLException e) {
            context.sessionAttribute(FLASH, INCORRECT_URL);
            context.sessionAttribute(FLASH_TYPE, "danger");
            context.redirect(ROOT_PATH);
        } catch (SQLException e) {
            LOGGER.error("Error while saving Url to DB");
        }
    }

    /**
     * @param context
     * @param s
     */
    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        //method not implemented, because not used
    }

    /**
     * @param context
     */

    @Override
    public void getAll(@NotNull Context context) {
        List<Url> urls = null;
        try {
            urls = UrlRepository.getEntities();
        } catch (SQLException e) {
            LOGGER.error("Error when retrieving all Urls");
        }
        UrlsPage page = new UrlsPage(urls);
        context.render("url/index.jte", Collections.singletonMap("page", page));

    }

    /**
     * @param context
     * @param s
     */
    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int id = context.pathParamAsClass("id", Integer.class).get();
        Url url = null;
        List<UrlCheck> urlChecks = new ArrayList<>();
        try {
            url = UrlRepository.find(id).orElseThrow(() -> new NotFoundResponse("Url with ID: " + id + " not found"));
        } catch (SQLException e) {
            LOGGER.error("Error when retrieving the Url");
        }

        try {
            urlChecks = UrlCheckRepository.getEntities(id);
        } catch (SQLException e) {
            LOGGER.error("Error when retrieving all Urls");
        }
        UrlPage page = new UrlPage(url);
        UrlChecksPage urlChecksPage = new UrlChecksPage(urlChecks);
        context.render("url/show.jte", Map.of("page", page, "urlChecksPage", urlChecksPage));
    }

    /**
     * @param context
     * @param s
     */
    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        //method not implemented, because not used
    }
}
