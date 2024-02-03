package hexlet.code;

// No new imports needed - using JUnit

import hexlet.code.model.Url;
import io.javalin.Javalin;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class UrlTest {

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
    public void testUrlConstructor() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Url url = new Url("test", timestamp);
        url.setId(10);

        assertNotNull(url.getId());
        assertEquals("test", url.getName());
        assertEquals(timestamp, url.getCreatedAt());
    }

    @Test
    public void testSetName() {
        Url url = new Url("original", new Timestamp(0));
        url.setName("updated");

        assertEquals("updated", url.getName());
    }

    @Test
    public void testSetCreatedAt() {
        Url url = new Url("test", new Timestamp(0));
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
        url.setCreatedAt(newTimestamp);

        assertEquals(newTimestamp, url.getCreatedAt());
    }
}

