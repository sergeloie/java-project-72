package hexlet.code.util;

public final class NamedRoutes {
    public static final String ROOT_PATH = "/";
    public static final String URL_PATH = "/urls";

    public static String getUrlPath(Long id) {
        return URL_PATH + "/" + id;
    }
    public static String getUrlCheck(Long id) {
        return URL_PATH + "/" + id + "/checks";
    }

}
