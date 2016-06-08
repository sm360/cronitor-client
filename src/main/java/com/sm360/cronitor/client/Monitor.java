package com.sm360.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class Monitor {

    private final static Logger logger = Logger.getLogger(Monitor.class.getName());

    public static final String CRONITOR_BASE_DOMAIN = "https://cronitor.link/%s/%s";

    private String authKey;
    private String monitorCode;

    public Monitor(String authKey, String monitorCode){

        this.authKey = authKey;
        this.monitorCode = monitorCode;
    }

    public void run() throws IOException {

        executeCommand(Command.RUN);
    }

    public void pause(Integer pauseTimeInHours) throws IOException {

        executeCommand(Command.PAUSE, pauseTimeInHours.toString());
    }

    public void complete() throws IOException {

        executeCommand(Command.COMPLETE);
    }

    public void fail() throws IOException {

        executeCommand(Command.FAIL);
    }

    private void executeCommand(Command command) throws IOException {
        executeCommand(command, null);
    }

    private void executeCommand(Command command, String parameter) throws IOException {

        logger.info(String.format("Calling cronitor [%s] using monitorCode[%s]", command, monitorCode));
        String url = String.format(CRONITOR_BASE_DOMAIN, monitorCode, command.getValue());
        if (parameter != null) {
            url += "/" + parameter;
        }
        callService(new URL(url));
    }

    private void callService(URL url) throws IOException {

        url = new URL(url.toString() + "?auth_key=" + authKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        connection.getInputStream();
        connection.disconnect();
    }
}
