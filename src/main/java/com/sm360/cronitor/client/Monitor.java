package com.sm360.cronitor.client;

import com.sm360.cronitor.client.commandexecutors.CompleteCommandExecutor;
import com.sm360.cronitor.client.commandexecutors.FailCommandExecutor;
import com.sm360.cronitor.client.commandexecutors.PauseCommandExecutor;
import com.sm360.cronitor.client.commandexecutors.RunCommandExecutor;

import java.io.IOException;
import java.net.URISyntaxException;

public class Monitor {

    private String authKey;
    private String monitorCode;

    public Monitor(String authKey, String monitorCode) {

        this.authKey = authKey;
        this.monitorCode = monitorCode;
    }

    public void run() throws IOException, URISyntaxException {
        new RunCommandExecutor().execute(monitorCode, authKey);
    }

    public void run(String message) throws IOException, URISyntaxException {
        new RunCommandExecutor().withMessage(message).execute(monitorCode, authKey);
    }

    public void pause(Integer pauseTimeInHours) throws IOException, URISyntaxException {
        new PauseCommandExecutor(pauseTimeInHours).execute(monitorCode, authKey);
    }

    public void unpause() throws IOException, URISyntaxException {
        new PauseCommandExecutor(0).execute(monitorCode, authKey);
    }

    public void complete() throws IOException, URISyntaxException {
        new CompleteCommandExecutor().execute(monitorCode, authKey);
    }

    public void complete(String message) throws IOException, URISyntaxException {
        new CompleteCommandExecutor().withMessage(message).execute(monitorCode, authKey);
    }

    public void fail() throws IOException, URISyntaxException {
        new FailCommandExecutor().execute(monitorCode, authKey);
    }

    public void fail(String message) throws IOException, URISyntaxException {
        new FailCommandExecutor().withMessage(message).execute(monitorCode, authKey);
    }
}
