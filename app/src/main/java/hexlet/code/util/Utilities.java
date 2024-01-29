package hexlet.code.util;

import org.apache.commons.validator.routines.UrlValidator;

import java.net.URI;

public class Utilities {

    public static String uriToString(URI uri) {
        return uri.getScheme() + "://" + uri.getHost()
                + (uri.getPort() != -1 ? ":" + uri.getPort() : "");
    }

    public static boolean isValidUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
