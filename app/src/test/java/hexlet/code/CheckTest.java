package hexlet.code;

import io.javalin.Javalin;
import kong.unirest.Empty;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CheckTest {

    private static Javalin app;
    private static MockWebServer mockWebServer;
    private static String baseUrl;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        app = App.getApp();
        app.start(7070);
        baseUrl = "http://localhost:7070";

        mockWebServer = new MockWebServer();
    }

    @AfterEach
    public void tearDown() throws IOException {
        app.stop();
        mockWebServer.shutdown();

    }

    @Test
    void testMockServer() throws IOException {


        String expected = Files.readString(Path.of("src/test/resources/test.html"));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(expected);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        mockWebServer.enqueue(new MockResponse().setBody(expected));
        String address = mockWebServer.url("/").toString();

        //Put test server url in DB
        HttpResponse<Empty> putTestUrlResponse = Unirest
                .post(baseUrl + "/urls")
                .field("url", address)
                .asEmpty();

        String checkIfUrlPresent = Unirest
                .get(baseUrl + "/urls")
                .asString()
                .getBody();
        assertTrue(checkIfUrlPresent.contains(address.substring(0, address.length() - 1)));

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/urls/1/checks")
                .asString();

        String checkIfUrlCheckPresent = Unirest
                .get(baseUrl + "/urls/1")
                .asString()
                .getBody();

//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(checkIfUrlCheckPresent);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        assertTrue(checkIfUrlCheckPresent.contains("Title test message"));
        assertTrue(checkIfUrlCheckPresent.contains("its description"));
        assertTrue(checkIfUrlCheckPresent.contains("Header01"));
    }
}
