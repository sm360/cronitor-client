package ca.sm360.cronitor.client.urlgenerators;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class CommandUrlGenerator {

    private static final String BASE_URL = "https://cronitor.link/%s";

    public URL buildURI(String monitorCode, String authKey) throws URISyntaxException, MalformedURLException {

        String baseUrl = String.format(BASE_URL, monitorCode);

        URIBuilder uriBuilder = generateURIBuilder(baseUrl);
        if (authKey != null) {
            uriBuilder.addParameter("auth_key", authKey);
        }
        return uriBuilder.build().toURL();
    }

    protected abstract URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException;
}
