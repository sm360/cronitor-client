package com.sm360.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class CronitorClient {

    private final static Logger logger = Logger.getLogger(CronitorClient.class.getName());

    public static final String CRONITOR_BASE_DOMAIN = "https://cronitor.link/%s/%s";

    public void run(String monitorCode) throws IOException {

        executeCommand(Command.RUN, monitorCode);
    }

    public void pause(String monitorCode, Integer pauseTimeInHours) throws IOException {

        executeCommand(Command.PAUSE, monitorCode, pauseTimeInHours.toString());
    }

    public void complete(String monitorCode) throws IOException {

        executeCommand(Command.COMPLETE, monitorCode);
    }

    public void fail(String monitorCode) throws IOException {

        executeCommand(Command.FAIL, monitorCode);
    }

    private void executeCommand(Command command, String monitorCode) throws IOException {
        executeCommand(command, monitorCode, null);
    }

    private void executeCommand(Command command, String monitorCode, String parameter) throws IOException {

        logger.info(String.format("Calling cronitor [%s] using monitorCode[%s]", command, monitorCode));
        String url = String.format(CRONITOR_BASE_DOMAIN, monitorCode, command.getValue());
        if (parameter != null) {
            url += "/" + parameter;
        }
        callService(new URL(url));
    }

    private void callService(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        connection.getInputStream();
        connection.disconnect();
    }
}
