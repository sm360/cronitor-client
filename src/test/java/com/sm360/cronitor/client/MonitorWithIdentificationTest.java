package com.sm360.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MonitorWithIdentificationTest {

    @InjectMocks
    private CronitorClient client = new CronitorClient("customAuthKey");
    @Mock
    private CronitorPinger cronitorPinger;

    private String monitorCode = "customMonitorCode";

    @Test
    public void can_start_monitor_with_minimal_requirements() throws Exception {

        client.run(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/run?auth_key=customAuthKey"));
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorCode, "customRunMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/run?msg=customRunMessage&auth_key=customAuthKey"));
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/complete?auth_key=customAuthKey"));
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorCode, "customCompleteMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/complete?msg=customCompleteMessage&auth_key=customAuthKey"));
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/fail?auth_key=customAuthKey"));
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorCode, "customFailMessage");

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/fail?msg=customFailMessage&auth_key=customAuthKey"));
    }

    @Test
    public void can_pause_monitor() throws Exception {

        client.pause(monitorCode, 5);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/pause/5?auth_key=customAuthKey"));
    }

    @Test
    public void can_unpause_monitor() throws Exception {

        client.unpause(monitorCode);

        verify(cronitorPinger).ping(new URL("https://cronitor.link/customMonitorCode/pause/0?auth_key=customAuthKey"));
    }
}
