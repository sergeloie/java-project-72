package hexlet.code;

import hexlet.code.controller.RootController;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = getApp();
        app.start(getPort());
    }

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get(NamedRoutes.ROOT_PATH, RootController::show);

        return app;
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }
}
