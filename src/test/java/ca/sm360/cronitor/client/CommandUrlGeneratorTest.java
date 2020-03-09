package ca.sm360.cronitor.client;

import org.junit.Assert;
import org.junit.Test;


import java.net.URL;
import ca.sm360.cronitor.client.urlgenerators.CommandUrlGenerator;

public class CommandUrlGeneratorTest {

    private CommandUrlGenerator urlGenerator = new CommandUrlGenerator();
    private CommandUrlGenerator fallbackUrlGenerator = new CommandUrlGenerator(false);

    private String monitorCode = "customMonitorCode";

    @Test
    public void can_build_run_url() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.RUN.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/run"), runURL);
    }

    @Test
    public void can_build_complete_url() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.COMPLETE.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/complete"), runURL);
    }

    @Test
    public void can_build_fail_url() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.FAIL.getValue(), monitorCode, null, null);

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/fail"), runURL);
    }

    @Test
    public void can_build_pause_url() throws Exception {
        URL runURL = urlGenerator.buildPauseURI(monitorCode, 5, null);

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/pause/5"), runURL);
    }

    public void can_build_url_with_auth_key() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.RUN.getValue(), monitorCode, "customAuthKey", null);

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/run?auth_key=customAuthKey"), runURL);
    }

    public void can_build_url_with_msg() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.RUN.getValue(), monitorCode, null, "customMessage");

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/run?msg=customMessage"), runURL);
    }

    public void can_build_url_with_msg_and_auth_key() throws Exception {
        URL runURL = urlGenerator.buildURI(Command.RUN.getValue(), monitorCode, "customAuthKey", "customMessage");

        Assert.assertEquals(new URL("https://cronitor.link/customMonitorCode/run?auth_key=customAuthKey&msg=customMessage"), runURL);
    }

    @Test
    public void builds_url_with_fallback_domain() throws Exception {
        URL runURL = fallbackUrlGenerator.buildURI(Command.RUN.getValue(), monitorCode, null, null);
        Assert.assertEquals(new URL("https://cronitor.io/customMonitorCode/run"), runURL);
    }


}
