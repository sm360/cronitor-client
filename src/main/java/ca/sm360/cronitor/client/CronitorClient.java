package ca.sm360.cronitor.client;

import ca.sm360.cronitor.client.urlgenerators.CompleteCommandUrlGenerator;
import ca.sm360.cronitor.client.urlgenerators.FailCommandUrlGenerator;
import ca.sm360.cronitor.client.urlgenerators.PauseCommandUrlGenerator;
import ca.sm360.cronitor.client.urlgenerators.RunCommandUrlGenerator;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class CronitorClient {

    private final static Logger logger = Logger.getLogger(CronitorClient.class.getName());

    private CronitorPinger cronitorPinger = new CronitorPinger();

    private String apiKey;

    public CronitorClient() {

        this(null);
    }

    public CronitorClient(String apiKey) {

        this.apiKey = apiKey;
    }

    public void run(String monitorCode) throws IOException, URISyntaxException {
        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new RunCommandUrlGenerator().buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void run(String monitorCode, String message) throws IOException, URISyntaxException {
        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new RunCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new CompleteCommandUrlGenerator().buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorCode, String message) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new CompleteCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new FailCommandUrlGenerator().buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorCode, String message) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new FailCommandUrlGenerator().withMessage(message).buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void pause(String monitorCode, int timeoutInHours) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new PauseCommandUrlGenerator(timeoutInHours).buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("we can't pause monitor because the monitor code is null or empty");
        }
    }

    public void unpause(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            URL commandUrl = new PauseCommandUrlGenerator(0).buildURI(monitorCode, apiKey);
            cronitorPinger.ping(commandUrl);
        } else {
            logger.warning("we can't pause monitor because the monitor code is null or empty");
        }
    }
}
