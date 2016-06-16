package com.sm360.cronitor.client.commandexecutors;

import com.sm360.cronitor.client.Command;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class FailCommandUrlGenerator extends CommandWithMessageUrlGenerator {

    @Override
    protected URIBuilder generateURIBuilderIgnoringMessage(String baseURL) throws URISyntaxException {
        baseURL += "/" + Command.FAIL.getValue();
        return new URIBuilder(baseURL);
    }
}
