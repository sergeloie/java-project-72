package hexlet.code;

// No new imports needed - using JUnit

import hexlet.code.model.Url;
import io.javalin.Javalin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UrlTest {
    private Javalin app;

    @BeforeEach
    public final void setUp() throws SQLException {
        app = App.getApp();
    }

    @Test
    public void testUrlConstructor() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Url url = new Url("test", timestamp);
        url.setId(10L);

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

