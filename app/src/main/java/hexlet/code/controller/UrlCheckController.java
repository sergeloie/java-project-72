/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;

import static hexlet.code.util.NamedRoutes.getUrlPath;

@Slf4j
public class UrlCheckController {

    public static void create(Context context) throws SQLException {

        int urlId = context.pathParamAsClass("id", Integer.class).get();
        String address = UrlRepository.find(urlId).get().getName();

        try {
            HttpResponse<String> response = Unirest.get(address).asString();
            Document document = Jsoup.parse(response.getBody());

            int statusCode = response.getStatus();
            String title = document.title();
            Element elementH1 = document.selectFirst("h1");
            String h1 = elementH1 == null ? "" : elementH1.text();
            String description = document.select("meta[name=description]").attr("content");
//            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

            UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
            urlCheck.setUrlId(urlId);
//            urlCheck.setCreatedAt(createdAt.toInstant());
            UrlCheckRepository.save(urlCheck);

            context.sessionAttribute("flash", "Страница успешно проверена");
            context.sessionAttribute("flashType", "success");
            context.redirect(getUrlPath(urlId));

        } catch (UnirestException e) {
            context.sessionAttribute("flash", "Некорректный адрес");
            context.sessionAttribute("flashType", "danger");
            context.redirect(getUrlPath(urlId));
        } catch (Exception e) {
            context.sessionAttribute("flash", e.getMessage());
            context.sessionAttribute("flashType", "danger");
            context.redirect(getUrlPath(urlId));
        }
    }
}
