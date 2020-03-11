package io.cronitor.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import io.cronitor.client.urlgenerators.CommandUrlGenerator;

@RunWith(PowerMockRunner.class)
public class CommandUrlGeneratorTest {

    private CommandUrlGenerator urlGenerator = new CommandUrlGenerator();
    private CommandUrlGenerator fallbackUrlGenerator = new CommandUrlGenerator(false);

    private String monitorCode = "d3x0c1";

    @Test
    public void can_build_run_url() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.RUN.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/run"), runURL);
    }

    @Test
    public void can_build_complete_url() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.COMPLETE.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/complete"), runURL);
    }

    @Test
    public void can_build_fail_url() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.FAIL.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/fail"), runURL);
    }

    @Test
    public void can_build_pause_url() throws Exception {
        URL runURL = urlGenerator.buildPauseURI(monitorCode, 5, null);

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/pause/5"), runURL);
    }

    public void can_build_url_with_auth_key() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.RUN.getValue(), monitorCode, "customAuthKey", null);

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/run?auth_key=customAuthKey"), runURL);
    }

    public void can_build_url_with_msg() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.RUN.getValue(), monitorCode, null, "customMessage");

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/run?msg=customMessage"), runURL);
    }

    public void can_build_url_with_msg_and_auth_key() throws Exception {
        URL runURL = urlGenerator.buildURL(Command.RUN.getValue(), monitorCode, "customAuthKey", "customMessage");

        Assert.assertEquals(new URL("https://cronitor.link/d3x0c1/run?auth_key=customAuthKey&msg=customMessage"), runURL);
    }

    @Test
    public void builds_url_with_fallback_domain() throws Exception {
        URL runURL = fallbackUrlGenerator.buildURL(Command.RUN.getValue(), monitorCode, null, null);
        Assert.assertEquals(new URL("https://cronitor.io/d3x0c1/run"), runURL);
    }


}
