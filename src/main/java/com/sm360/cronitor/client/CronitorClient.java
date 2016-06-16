package com.sm360.cronitor.client;

import com.sm360.cronitor.client.urlgenerators.CompleteCommandUrlGenerator;
import com.sm360.cronitor.client.urlgenerators.FailCommandUrlGenerator;
import com.sm360.cronitor.client.urlgenerators.PauseCommandUrlGenerator;
import com.sm360.cronitor.client.urlgenerators.RunCommandUrlGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CronitorClient {

    private CronitorPinger cronitorPinger = new CronitorPinger();

    private String apiKey;

    public CronitorClient() {

        this(null);
    }

    public CronitorClient(String apiKey) {

        this.apiKey = apiKey;
    }

    public void run(String monitorCode) throws IOException, URISyntaxException {

        URL commandUrl = new RunCommandUrlGenerator().buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void run(String monitorCode, String message) throws IOException, URISyntaxException {

        URL commandUrl = new RunCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void complete(String monitorCode) throws URISyntaxException, IOException {
        URL commandUrl = new CompleteCommandUrlGenerator().buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void complete(String monitorCode, String message) throws URISyntaxException, IOException {
        URL commandUrl = new CompleteCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void fail(String monitorCode) throws URISyntaxException, IOException {
        URL commandUrl = new FailCommandUrlGenerator().buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void fail(String monitorCode, String message) throws URISyntaxException, IOException {
        URL commandUrl = new FailCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void pause(String monitorCode, int timeoutInHouirs) throws URISyntaxException, IOException {
        URL commandUrl = new PauseCommandUrlGenerator(timeoutInHouirs).buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }

    public void unpause(String monitorCode) throws URISyntaxException, IOException {
        URL commandUrl = new PauseCommandUrlGenerator(0).buildURI(monitorCode, apiKey);
        cronitorPinger.ping(commandUrl);
    }
}
