/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.controller;

import hexlet.code.dto.url.BuildUrlPage;
import io.javalin.http.Context;

import java.util.Collections;

public final class RootController {
    public static void show(Context context) {
        BuildUrlPage page = new BuildUrlPage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        page.setFlashType(context.consumeSessionAttribute("flashType"));
        context.render("index.jte", Collections.singletonMap("page", page));
    }
}
