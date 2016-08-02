package ca.sm360.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class CronitorPinger {

    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());

    private final Integer cronitorPingTimeoutInSecond = 10;

    public void ping(URL commandURL) throws IOException, URISyntaxException {

        logger.info("Will call url : " + commandURL);

        HttpURLConnection connection = (HttpURLConnection) commandURL.openConnection();
        connection.setConnectTimeout(cronitorPingTimeoutInSecond * 1000);

        try {
            connection.connect();
            connection.getInputStream();
            connection.disconnect();
        } catch (SocketTimeoutException e) {
            logger.warning(String.format("fail to call url [%s] because of a timeout trigger after %d seconds",
                    commandURL, cronitorPingTimeoutInSecond));
        }
    }
}
