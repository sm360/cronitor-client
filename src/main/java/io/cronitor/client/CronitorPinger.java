package io.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import io.cronitor.client.urlgenerators.CommandUrlGenerator;

public class CronitorPinger {

    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());

    private final Integer cronitorPingTimeoutInSecond = 5;

    public void ping(String command, String monitorCode, String apiKey, String message) throws IOException, URISyntaxException {

        for (int i=0; i<4; i++) {
            Boolean useFallbackDomain = i > 1;
            URL commandURL = new CommandUrlGenerator(useFallbackDomain).buildURI(command, monitorCode, apiKey, message);
            if (!this.ping(commandURL)) {
                continue;
            }
            return;
        }
    }

    public void pause(String monitorCode, int timeoutHours, String apiKey) throws IOException, URISyntaxException {
        for (int i=0; i<4; i++) {
            Boolean useFallbackDomain = i > 1;
            URL commandURL = new CommandUrlGenerator(useFallbackDomain).buildPauseURI(monitorCode, timeoutHours, apiKey);
            if (!this.ping(commandURL)) {
                continue;
            }
            return;
        }
    }

    private boolean ping(URL commandURL) throws IOException, URISyntaxException {
        HttpURLConnection connection = (HttpURLConnection) commandURL.openConnection();
        connection.setConnectTimeout(cronitorPingTimeoutInSecond * 1000);

        try {
            connection.connect();
            connection.getInputStream();
            if (connection.getResponseCode() >= 500 ) {
                logger.warning(String.format("Failed to call url [%s] : an error occurred : [%d]", commandURL, connection.getResponseCode()));
                return false;
            }
            connection.disconnect();
        } catch (SocketTimeoutException ignore) {
            logger.warning(String.format("Failed to call url [%s] : a timeout occurred after %d seconds : [%s]",
                    commandURL, cronitorPingTimeoutInSecond, ignore));
            return false;
        }
        return true;

    }
}
