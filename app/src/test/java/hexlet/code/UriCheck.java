package hexlet.code;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class UriCheck {


    @Test
    public void uriTest() {
        String str1 = "https://qwe.com:12345";
        String str2 = "ы.shtako";
        URI uri = null;


        try {
            uri = new URI(str2);
        } catch (URISyntaxException e) {
            System.out.println("!!!EXCEPTION!!!");
        }

        System.out.println(uri.toString());
        UrlValidator validator = new UrlValidator();
        System.out.println(validator.isValid(str1));

    }
}
