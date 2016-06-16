package com.sm360.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MonitorWithoutIdentificationTest {

    @InjectMocks
    private CronitorClient client = new CronitorClient();
    @Mock
    private CronitorPinger cronitorPinger;

    private String monitorCode = "customMonitorCode";

    @Test
    public void can_start_monitor_with_minimal_requirements() throws Exception {

        client.run(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/run"));
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorCode, "customRunMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/run?msg=customRunMessage"));
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/complete"));
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorCode, "customCompleteMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/complete?msg=customCompleteMessage"));
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/fail"));
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorCode, "customFailMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/fail?msg=customFailMessage"));
    }

    @Test
    public void can_pause_monitor() throws Exception {

        client.pause(monitorCode, 5);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/pause/5"));
    }

    @Test
    public void can_unpause_monitor() throws Exception {

        client.unpause(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/pause/0"));
    }
}
