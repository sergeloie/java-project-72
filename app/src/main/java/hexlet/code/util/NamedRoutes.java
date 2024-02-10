/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.util;

public final class NamedRoutes {
    public static final String ROOT_PATH = "/";
    public static final String URL_PATH = "/urls";

    public static String getUrlPath(int id) {
        return URL_PATH + "/" + id;
    }
    public static String getUrlCheck(int id) {
        return URL_PATH + "/" + id + "/checks";
    }

}
