package hexlet.code;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

public final class AppTest {

    private Javalin app;

    @BeforeEach
    public void setUp() throws SQLException {
        app = App.getApp();
    }

    @Test
    void dummyTest() {
        assertTrue(true);
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get(NamedRoutes.ROOT_PATH);
            assertThat(response.code()).isEqualTo(200);
            assert response.body() != null;
            assertThat(response.body().string()).contains("Hello World");
        });
    }

    @Test
    public void testGetPort() {
        String defaultPort = String.valueOf(App.getPort());
        assertEquals("7070", defaultPort);
    }

    @Test
    public void testGetDatabaseUrl() {
        String defaultUrl = App.getDatabaseUrl();
        assertEquals("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;", defaultUrl);
    }
}
