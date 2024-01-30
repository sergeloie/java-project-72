package hexlet.code.util;

import java.net.URI;

public class Utilities {

    public static String uriToString(URI uri) {
        return uri.getScheme() + "://" + uri.getHost()
                + (uri.getPort() != -1 ? ":" + uri.getPort() : "");
    }
}
