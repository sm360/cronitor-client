import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class CronitorClient {

    private final static Logger logger = Logger.getLogger(CronitorClient.class.getName());

    public static final String CRONITOR_BASE_DOMAIN = "https://cronitor.link/%s/%s";

    public void run(String monitorCode) throws IOException {

        logger.info(String.format("Calling cronitor run using monitorCode[%s]", monitorCode));
        URL cronitorStartUrl = new URL(String.format(CRONITOR_BASE_DOMAIN, monitorCode, "run"));
        callService(cronitorStartUrl);
    }

    public void pause(String monitorCode, Integer pauseTimeInHours) throws IOException {

        logger.info(String.format("Calling cronitor pause using monitorCode[%s]", monitorCode));
        URL cronitorStartUrl = new URL(String.format(CRONITOR_BASE_DOMAIN + "/%s", monitorCode, "pause", pauseTimeInHours));
        callService(cronitorStartUrl);
    }

    public void complete(String monitorCode) throws IOException {

        logger.info(String.format("Calling cronitor complete using monitorCode[%s]", monitorCode));
        URL cronitorStartUrl = new URL(String.format(CRONITOR_BASE_DOMAIN, monitorCode, "complete"));
        callService(cronitorStartUrl);
    }

    public void fail(String monitorCode) throws IOException {

        logger.info(String.format("Calling cronitor fail using monitorCode[%s]", monitorCode));
        URL cronitorStartUrl = new URL(String.format(CRONITOR_BASE_DOMAIN, monitorCode, "fail"));
        callService(cronitorStartUrl);
    }

    private void callService(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        connection.getInputStream();
        connection.disconnect();
    }
}
