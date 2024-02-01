package hexlet.code;

import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

public class UnirestTest {

    @Test
    public void Test1() {

        String address = "https://ru.hexlet.io";
        HttpResponse<String> response = Unirest.get(address).asString();

        long statusCode = response.getStatus();
        System.out.println(statusCode);

        Document document = Jsoup.parse(response.getBody());
        System.out.println("Title = " + document.title());

        Element h1 = document.selectFirst("h1");
        if (h1 != null) {
            System.out.println("H1 = " + h1.text());
        }
        String description = document.select("meta[name=description]").attr("content");
        System.out.println("Description = " + description);

}}
