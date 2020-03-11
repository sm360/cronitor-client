package io.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import io.cronitor.client.urlgenerators.CommandUrlGenerator;

public class CronitorPinger {

    public CronitorPinger() {

        this.connection = null;
    }



    public HttpURLConnection connection;
    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());
    private final Integer cronitorPingTimeoutInSecond = 5;

    public void ping(String command, String monitorCode, String apiKey, String message) throws IOException, URISyntaxException {
        logger.warning(String.format("PING"));
        for (int i=0; i<4; i++) {
            Boolean usePrimaryPingDomain = i < 2;
            setConnection(getURL(usePrimaryPingDomain, command, monitorCode, apiKey, message));
            if (_ping()) { return; }
        }
    }

    public void pause(String monitorCode, int timeoutHours, String apiKey) throws IOException, URISyntaxException {
        for (int i=0; i<4; i++) {
            Boolean usePrimaryPingDomain = i < 2;
            setConnection(getURL(usePrimaryPingDomain, monitorCode, timeoutHours, apiKey));
            if (_ping()) { return; }
        }
    }

	public URL getURL(Boolean usePrimaryPingDomain, String command, String monitorCode, String apiKey, String message) throws IOException, URISyntaxException {
		return new CommandUrlGenerator(usePrimaryPingDomain).buildURI(command, monitorCode, apiKey, message);
    }

    public URL getURL(Boolean usePrimaryPingDomain, String monitorCode, int timeoutHours, String apiKey) throws IOException, URISyntaxException {
		return new CommandUrlGenerator(usePrimaryPingDomain).buildPauseURI(monitorCode, timeoutHours, apiKey);
	}

    private void setConnection(URL url) {
        try {
            this.connection = (HttpURLConnection) url.openConnection();
            this.connection.setConnectTimeout(cronitorPingTimeoutInSecond * 1000);
        } catch (IOException e) {
            logger.warning(String.format("unable to establish connection to %s", url.getPath()));
        }
      }

    private boolean _ping() throws URISyntaxException {
        try {
            this.connection.connect();
            logger.warning(String.format("AFTER CONNECT!"));
            this.connection.getInputStream();
            logger.warning(String.format("AFTER INPUTSTREAM"));
            if (this.connection.getResponseCode() >= 500 ) {
                logger.warning(String.format("Failed to call url [%s] : an error occurred : [%d]", this.connection.getURL(), this.connection.getResponseCode()));
                return false;
            }
            this.connection.disconnect();
        } catch (SocketTimeoutException ignore) {
            logger.warning(String.format("Failed to call url [%s] : a timeout occurred after %d seconds : [%s]", this.connection.getURL(), cronitorPingTimeoutInSecond, ignore));
            return false;
        } catch (IOException e) {
            logger.warning(String.format("unable to establish connection to %s", this.connection.getURL()));
        }
        return true;

    }

}
