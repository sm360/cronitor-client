package io.cronitor.client.urlgenerators;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CommandUrlGenerator {

    private static final String BASE_URL = "https://cronitor.link/%s/%s";
    private static final String FALLBACK_BASE_URL = "https://cronitor.io/%s/%s";
    private static final String PAUSE_BASE_URL = "https://cronitor.link/%s/pause/%s";
    private static final String FALLBACK_PAUSE_BASE_URL = "https://cronitor.io/%s/pause/%s";
    private Boolean usePrimaryPingDomain;

    public CommandUrlGenerator() {

        this.usePrimaryPingDomain = true;
    }

    public CommandUrlGenerator(Boolean usePrimaryPingDomain) {

        this.usePrimaryPingDomain = usePrimaryPingDomain;
    }


    public URL buildURL(String command, String monitorCode, String authKey, String message) throws URISyntaxException, MalformedURLException {
        String baseURL;
        if (usePrimaryPingDomain) {
            baseURL = CommandUrlGenerator.BASE_URL;
        } else {
            baseURL = CommandUrlGenerator.FALLBACK_BASE_URL;
        }

        URIBuilder uriBuilder = new URIBuilder(String.format(baseURL, monitorCode, command));

        if (authKey != null) {
            uriBuilder.addParameter("auth_key", authKey);
        }
        if (message != null) {
            uriBuilder.addParameter("msg", authKey);
        }

        return uriBuilder.build().toURL();
    }

    public URL buildPauseURI(String monitorCode, int pauseHours, String authKey) throws URISyntaxException, MalformedURLException {
        String baseURL;
        if (usePrimaryPingDomain) {
            baseURL = CommandUrlGenerator.PAUSE_BASE_URL;
        } else {
            baseURL = CommandUrlGenerator.FALLBACK_PAUSE_BASE_URL;
        }

        URIBuilder uriBuilder = new URIBuilder(String.format(baseURL, monitorCode, pauseHours));

        if (authKey != null) {
            uriBuilder.addParameter("auth_key", authKey);
        }

        return uriBuilder.build().toURL();
    }
}
