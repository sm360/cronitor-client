package com.sm360.cronitor.client.commandexecutors;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public abstract class CommandExecutor {

    private final static Logger logger = Logger.getLogger(CommandExecutor.class.getName());

    private static final String BASE_URL = "https://cronitor.link/%s";

    public void execute(String monitorCode, String authKey) throws URISyntaxException, IOException {

        String baseUrl = String.format(BASE_URL, monitorCode);

        URIBuilder uriBuilder = generateURIBuilder(baseUrl);
        uriBuilder.addParameter("auth_key", authKey);
        callService(uriBuilder);
    }

    protected abstract URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException, MalformedURLException;

    private void callService(URIBuilder uriBuilder) throws IOException, URISyntaxException {

        URL url = uriBuilder.build().toURL();

        logger.info("Will call url : " + url);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        connection.getInputStream();
        connection.disconnect();
    }
}
