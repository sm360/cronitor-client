package com.sm360.cronitor.client.urlgenerators;

import com.sm360.cronitor.client.Command;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class RunCommandUrlGenerator extends CommandWithMessageUrlGenerator {

    @Override
    protected URIBuilder generateURIBuilderIgnoringMessage(String baseURL) throws URISyntaxException {

        baseURL += "/" + Command.RUN.getValue();
        return new URIBuilder(baseURL);
    }
}
