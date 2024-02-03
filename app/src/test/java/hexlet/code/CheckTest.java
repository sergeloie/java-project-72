package hexlet.code;

import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import kong.unirest.Empty;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CheckTest {

    private static Javalin app;
    private static MockWebServer mockWebServer;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        app = App.getApp();
        app.start(7070);
        baseUrl = "http://localhost:7070";
        mockWebServer = new MockWebServer();
    }


    @AfterAll
    public static void afterAll() throws IOException {
        mockWebServer.shutdown();
        app.stop();
    }

    @Test
    void testMockServer() throws IOException, SQLException {


        String expected = Files.readString(Path.of("src/test/resources/test.html"));
        mockWebServer.enqueue(new MockResponse().setBody(expected));
        String address = mockWebServer.url("/").toString();
        String validUrl = address.substring(0, address.length() - 1);

        //Put test server url in DB
        HttpResponse<Empty> putTestUrlResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", address)
                .asEmpty();



        String checkIfUrlPresent = Unirest
                .get(baseUrl + "/urls")
                .asString()
                .getBody();
        assertTrue(checkIfUrlPresent.contains(validUrl));

        int urlId = UrlRepository.find(validUrl).get().getId();

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/urls/" + urlId + "/checks")
                .asString();

        String checkIfUrlCheckPresent = Unirest
                .get(baseUrl + "/urls/" + urlId)
                .asString()
                .getBody();

        assertTrue(checkIfUrlCheckPresent.contains("Title test message"));
        assertTrue(checkIfUrlCheckPresent.contains("its description"));
        assertTrue(checkIfUrlCheckPresent.contains("Header01"));
    }

    @Test
    void testRootPage() {
        HttpResponse<String> httpResponse = Unirest.get(baseUrl).asString();
        assertTrue(httpResponse.isSuccess());
        assertEquals(200, httpResponse.getStatus());
        assertTrue(httpResponse.getBody().contains("Page Analyzer"));
    }

    @Test
    void testAddUrl() throws SQLException {
        String rawUrl = "https://ru.hexlet.io/projects/72/members/36557";
        String correctUrl = "https://ru.hexlet.io";

        HttpResponse<String> postUrlResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", rawUrl)
                .asString();

        String getUrlsPageResponse = Unirest
                .get(baseUrl + "/urls")
                .asString()
                .getBody();

        assertTrue(getUrlsPageResponse.contains(correctUrl));

        int urlId = UrlRepository.find(correctUrl).get().getId();

        String getUrlPageResponse = Unirest
                .get(baseUrl + "/urls/" + urlId)
                .asString()
                .getBody();

        assertTrue(getUrlPageResponse.contains(correctUrl));
    }

    @Test
    void testAddInvalidUrl() {
        String incorrectUrl = "no url";

        HttpResponse<String> httpResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", incorrectUrl)
                .asString();
        HttpResponse<String> checkResponse = Unirest
                .get(baseUrl)
                .asString();
        assertTrue(checkResponse.getBody().contains("Некорректный URL"));
    }

    @Test
    void testDoubleUrl() {
        String doubleUrl = "https://ru.hexlet.io";
        HttpResponse<String> firstResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", doubleUrl)
                .asString();
        HttpResponse<String> secondResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", doubleUrl)
                .asString();
        HttpResponse<String> checkResponse = Unirest
                .get(baseUrl)
                .asString();
        assertTrue(checkResponse.getBody().contains("Страница уже существует"));

    }
}
