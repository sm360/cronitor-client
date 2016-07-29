package ca.sm360.cronitor.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class CronitorPinger {

    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());

    public void ping(URL commandURL) throws IOException, URISyntaxException {

        logger.info("Will call url : " + commandURL);

        HttpURLConnection connection = (HttpURLConnection) commandURL.openConnection();
        connection.connect();
        connection.getInputStream();
        connection.disconnect();
    }
}
