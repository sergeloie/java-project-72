package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;


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
//            assertThat(response.body().string()).contains("Hello World");
        });
    }

}
