package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UrlCheckController {

    public static void create(Context context) throws SQLException {

        String address = context.formParam("address");
        long url_id = UrlRepository.find(address).get().getId();

        HttpResponse<String> response = Unirest.get(address).asString();

        long statusCode = response.getStatus();
        Document document = Jsoup.parse(response.getBody());

        String title = document.title();
        Element elementH1 = document.selectFirst("h1");
        String h1 = elementH1 == null ? "" : elementH1.text();
        String description = document.select("meta[name=description]").attr("content");
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

        UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description, url_id, createdAt);



    }
}
