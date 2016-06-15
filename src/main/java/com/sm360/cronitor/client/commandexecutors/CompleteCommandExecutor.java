package com.sm360.cronitor.client.commandexecutors;

import com.sm360.cronitor.client.Command;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class CompleteCommandExecutor extends CommandExecutor {

    private String message;

    public CompleteCommandExecutor withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    protected URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException, MalformedURLException {

        baseURL += "/" + Command.COMPLETE.getValue();
        URIBuilder uriBuilder = new URIBuilder(baseURL);
        return uriBuilder.addParameter("msg", message);
    }
}
