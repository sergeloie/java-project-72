/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static hexlet.code.util.NamedRoutes.getUrlPath;
import static hexlet.code.util.ResourceRoutes.CHECK_ADDED;
import static hexlet.code.util.ResourceRoutes.FLASH;
import static hexlet.code.util.ResourceRoutes.FLASH_TYPE;
import static hexlet.code.util.ResourceRoutes.INCORRECT_ADDRESS;

public class UrlCheckController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlCheckController.class);

    public static void create(Context context) throws SQLException {

        int urlId = context.pathParamAsClass("url-id", Integer.class).get();
        String address = UrlRepository.find(urlId).get().getName();

        HttpResponse<String> response = Unirest.get(address).asString();

        response.ifFailure(response1 -> {
            context.sessionAttribute(FLASH, INCORRECT_ADDRESS);
            context.sessionAttribute(FLASH_TYPE, "danger");
            context.redirect(getUrlPath(urlId));
        }).ifSuccess(response1 -> {
            int statusCode = response.getStatus();
            Document document = Jsoup.parse(response.getBody());
            String title = document.title();
            Element elementH1 = document.selectFirst("h1");
            String h1 = elementH1 == null ? "" : elementH1.text();
            String description = document.select("meta[name=description]").attr("content");
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
            UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description, urlId, createdAt);
            try {
                UrlCheckRepository.save(urlCheck);
            } catch (SQLException e) {
                LOGGER.error("Error while saving urlCheck to DB");
            }
            context.sessionAttribute(FLASH, CHECK_ADDED);
            context.sessionAttribute(FLASH_TYPE, "success");
            context.redirect(getUrlPath(urlId));
        });
    }
}
