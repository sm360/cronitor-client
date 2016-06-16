package com.sm360.cronitor.client.commandexecutors;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public abstract class CommandWithMessageUrlGenerator extends CommandUrlGenerator {

    private String message;

    public CommandWithMessageUrlGenerator withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    protected URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException {

        URIBuilder uriBuilder = generateURIBuilderIgnoringMessage(baseURL);
        if (message != null) {
            uriBuilder.addParameter("msg", message);
        }
        return uriBuilder;
    }

    protected abstract URIBuilder generateURIBuilderIgnoringMessage(String baseURL) throws URISyntaxException;
}
