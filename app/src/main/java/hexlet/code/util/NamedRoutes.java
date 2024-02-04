package hexlet.code.util;

public final class NamedRoutes {
    private NamedRoutes() {
        throw new IllegalStateException("Utility class");
    }
    public static final String ROOT_PATH = "/";
    public static final String URL_PATH = "/urls";

    public static String getUrlPath(int id) {
        return URL_PATH + "/" + id;
    }
    public static String getUrlCheck(int id) {
        return URL_PATH + "/" + id + "/checks";
    }

}
