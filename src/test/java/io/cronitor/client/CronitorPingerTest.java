package io.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import java.net.HttpURLConnection;


@PrepareForTest({ URL.class, HttpURLConnection.class, CronitorPinger.class })
@RunWith(PowerMockRunner.class)
public class CronitorPingerTest {


    @Mock
    private CronitorPinger pinger = new CronitorPinger();

    private String code = "d3x0c1";
    private String command = "run";
    private String apiKey = null;
    private String message = null;
    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());

    @Test
    public void canPingCronitorLink() throws Exception {
        // set the url generated by passing usePrimaryPingDomain=true to setURL
        URL url = pinger.getURL(true, command, code, apiKey, message);
        pinger.setConnection(url);
        pinger._ping();
        assertEquals(pinger.connection.getResponseCode(), HttpURLConnection.HTTP_OK);
        assertEquals(pinger.connection.getURL().toString(), "https://cronitor.link/d3x0c1/run");
    }

    @Test
    public void canPingCronitorIO() throws Exception {
        // set the url generated by passing usePrimaryPingDomain=false to setURL
        URL url = pinger.getURL(false, command, code, apiKey, message);
        pinger.setConnection(url);
        pinger._ping();
        assertEquals(pinger.connection.getResponseCode(), HttpURLConnection.HTTP_OK);
        assertEquals(pinger.connection.getURL().toString(), "https://cronitor.io/d3x0c1/run");
    }

    @Test
    public void shouldPingAgainAfterTimeout() throws Exception {
        URL url = new URL("https://cronitor.link:81/d3x0c1/run");
        PowerMockito.when(pinger.getURL(true, command, code, apiKey, message)).thenReturn(url);
        pinger.ping(command, code, apiKey, message);
        verify(pinger, atLeast(2))._ping();
    }
}
