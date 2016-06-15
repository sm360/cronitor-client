package com.sm360.cronitor.client.commandexecutors;

import com.sm360.cronitor.client.Command;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class PauseCommandExecutor extends CommandExecutor {

    private Integer timeoutInHours;

    public PauseCommandExecutor(Integer timeoutInHours) {
        this.timeoutInHours = timeoutInHours;
    }

    @Override
    protected URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException, MalformedURLException {

        baseURL += "/" + Command.PAUSE.getValue();
        baseURL += "/" + timeoutInHours;
        return new URIBuilder(baseURL);
    }
}
