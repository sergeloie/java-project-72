package hexlet.code.controller;

import io.javalin.http.Context;

public class RootController {
    public static void show(Context context) {
        context.result("Hello World");
    }
}