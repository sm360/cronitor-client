package io.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;


@PrepareForTest({ URL.class, HttpURLConnection.class, CronitorPinger.class })
@RunWith(PowerMockRunner.class)
public class CronitorPingerTest {


    @InjectMocks
    private CronitorPinger pinger = new CronitorPinger();

    private String code = "d3x0c1";
    private String command = "run";
    private String apiKey = null;
    private String message = null;
    private final static Logger logger = Logger.getLogger(CronitorPinger.class.getName());

    @Test
    public void testCanPingCronitor() throws Exception {
        pinger.ping("run", "d3x0c1", null, null);
        assertEquals(pinger.connection.getResponseCode(), HttpURLConnection.HTTP_OK);
        assertEquals(pinger.connection.getURL().toString(), "https://cronitor.link/run/d3x0c1");
    }

    @Test
    public void shouldRetryCronitorLinkAfterTimeout() throws Exception {
        pinger.ping("run", "d3x0c1", null, null);
        assertEquals(pinger.connection.getResponseCode(), HttpURLConnection.HTTP_OK);
        assertEquals(pinger.connection.getURL().toString(), "https://cronitor.link/run/d3x0c1");
    }

    @Test
    public void retries_cronitor_link_after_500_error() throws Exception {

    }

    @Test
    public void retries_cronitor_io_after_two_timeouts() throws Exception {

    }

    @Test
    public void retries_cronitor_io_after_two_500_errors() throws Exception {

    }

    public static URL getMockUrl() throws IOException {
        final File file = new File("test.html");
        // assertTrue("Mock HTML File " + filename + " not found", file.exists());
        final URLConnection mockConnection = PowerMockito.mock(URLConnection.class);
        PowerMockito.when(mockConnection.getInputStream()).thenReturn(new FileInputStream(file));

        final URLStreamHandler handler = new URLStreamHandler() {

            @Override
            protected URLConnection openConnection(final URL arg0)
                    throws IOException {
                return mockConnection;
            }
        };

        final URL url = new URL("http://foo.bar", "foo.bar", 80, "", handler);
        return url;
    }
}
