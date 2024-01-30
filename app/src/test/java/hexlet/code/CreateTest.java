package hexlet.code;

import hexlet.code.controller.UrlController;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateTest {

    private final Context context = mock(Context.class);

    private Javalin app;

    @BeforeEach
    public final void setUp() throws SQLException {
        app = App.getApp();
    }

    @Test
    void testCreateValidUrl() throws SQLException {
        UrlController controller = new UrlController();
        when(context.formParam("url")).thenReturn("https://example.com");
        when(context.sessionAttribute("flashType")).thenReturn("success");
        controller.create(context);
        assertTrue(UrlRepository.find("https://example.com").isPresent());
        assertTrue(UrlRepository.find(1L).isPresent());
        assertEquals("https://example.com", UrlRepository.getEntities().get(0).getName());
        assertEquals("success", context.sessionAttribute("flashType"));
    }

    @Test
    void testCreateInvalidUrl() throws SQLException {
        UrlController controller = new UrlController();
        when(context.formParam("url")).thenReturn("invalid");
        when(context.sessionAttribute("flashType")).thenReturn("danger");

        controller.create(context);

        assertFalse(UrlRepository.find("invalid").isPresent());
        assertEquals("danger", context.sessionAttribute("flashType"));
    }

    @Test
    void testCreateDuplicateUrl() throws SQLException {
        UrlController controller = new UrlController();
        UrlRepository.save(new Url("https://example.com", Timestamp.valueOf(LocalDateTime.now())));
        when(context.formParam("url")).thenReturn("https://example.com");
        when(context.sessionAttribute("flashType")).thenReturn("danger");

        controller.create(context);

        assertTrue(UrlRepository.find("https://example.com").isPresent());
        assertEquals("danger", context.sessionAttribute("flashType"));
    }
}
