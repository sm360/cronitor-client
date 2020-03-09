package ca.sm360.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// import java.net.URL;

import static org.mockito.Mockito.verify;
import ca.sm360.cronitor.client.Command;

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

        // verify(cronitorPinger).ping(Command.RUN.getValue(), "customMonitorCode", "customAuthKey", null);
        verify(cronitorPinger).ping("run", "customMonitorCode", "customAuthKey", null);
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorCode, "customRunMessage");
        // verify(cronitorPinger).ping(Command.RUN.getValue(), "customMonitorCode", "customAuthKey", "customRunMessage");
        verify(cronitorPinger).ping("run", "customMonitorCode", "customAuthKey", "customRunMessage");
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorCode);
        // verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "customMonitorCode", "customAuthKey", null);
        verify(cronitorPinger).ping("complete", "customMonitorCode", "customAuthKey", null);
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorCode, "customCompleteMessage");
        // verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "customMonitorCode", "customAuthKey", "customRunMessage");
        verify(cronitorPinger).ping("complete", "customMonitorCode", "customAuthKey", "customCompleteMessage");
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorCode);
        // verify(cronitorPinger).ping(Command.FAIL.getValue(), "customMonitorCode", "customAuthKey", "customRunMessage");
        verify(cronitorPinger).ping("fail", "customMonitorCode", "customAuthKey", null);
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorCode, "customFailMessage");

        // verify(cronitorPinger).ping(Command.FAIL.getValue(), "customMonitorCode", "customAuthKey", "customRunMessage");
        verify(cronitorPinger).ping("fail", "customMonitorCode", "customAuthKey", "customFailMessage");
    }

    @Test
    public void can_pause_monitor() throws Exception {

        client.pause(monitorCode, 5);

        verify(cronitorPinger).pause(monitorCode, 5, "customAuthKey");
    }

    @Test
    public void can_unpause_monitor() throws Exception {

        client.unpause(monitorCode);

        verify(cronitorPinger).pause(monitorCode, 0, "customAuthKey");
    }
}
