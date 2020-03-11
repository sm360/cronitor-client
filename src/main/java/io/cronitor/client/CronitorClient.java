package io.cronitor.client;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
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
            cronitorPinger.ping(Command.RUN.getValue(), monitorCode, apiKey, null);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void run(String monitorCode, String message) throws IOException, URISyntaxException {
        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.ping(Command.RUN.getValue(), monitorCode, apiKey, message);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.ping(Command.COMPLETE.getValue(), monitorCode, apiKey, null);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorCode, String message) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.ping(Command.COMPLETE.getValue(), monitorCode, apiKey, message);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.ping(Command.FAIL.getValue(), monitorCode, apiKey, null);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorCode, String message) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.ping(Command.FAIL.getValue(), monitorCode, apiKey, message);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void pause(String monitorCode, int timeoutInHours) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.pause(monitorCode, timeoutInHours, apiKey);
        } else {
            logger.warning("we can't pause monitor because the monitor code is null or empty");
        }
    }

    public void unpause(String monitorCode) throws URISyntaxException, IOException {

        if(StringUtils.isNotBlank(monitorCode)) {
            cronitorPinger.pause(monitorCode, 0, apiKey);
        } else {
            logger.warning("we can't pause monitor because the monitor code is null or empty");
        }
    }
}
