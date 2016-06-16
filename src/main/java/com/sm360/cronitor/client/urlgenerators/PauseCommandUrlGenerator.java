package com.sm360.cronitor.client.urlgenerators;

import com.sm360.cronitor.client.Command;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class PauseCommandUrlGenerator extends CommandUrlGenerator {

    private Integer timeoutInHours;

    public PauseCommandUrlGenerator(Integer timeoutInHours) {
        this.timeoutInHours = timeoutInHours;
    }

    @Override
    protected URIBuilder generateURIBuilder(String baseURL) throws URISyntaxException {

        baseURL += "/" + Command.PAUSE.getValue();
        baseURL += "/" + timeoutInHours;
        return new URIBuilder(baseURL);
    }
}
