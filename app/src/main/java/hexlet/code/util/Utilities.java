/*
* This is a personal academic project. Dear PVS-Studio, please check it.
* PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
*/
package hexlet.code.util;

import java.net.URI;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Utilities {


    public static String uriToString(URI uri) {
        return uri.getScheme() + "://" + uri.getHost()
                + (uri.getPort() != -1 ? ":" + uri.getPort() : "");
    }

    public static String getPrettyDate(Timestamp timestamp) {
        return timestamp
                .toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));
    }
}
