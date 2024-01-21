package hexlet.code;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

public final class AppTest {

    private Javalin app;

    @BeforeEach
    public void setUp() {
        app = App.getApp();
    }

    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.ROOT_PATH);
            assertThat(response.code()).isEqualTo(200);
            assert response.body() != null;
            assertThat(response.body().string()).contains("Hello World");
        });
    }
}
