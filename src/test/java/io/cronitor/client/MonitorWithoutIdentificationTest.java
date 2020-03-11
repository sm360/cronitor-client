package io.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

// import java.net.URL;
import io.cronitor.client.Command;
import static org.mockito.Mockito.verify;


@RunWith(PowerMockRunner.class)
public class MonitorWithoutIdentificationTest {

    @InjectMocks
    private CronitorClient client = new CronitorClient();
    @Mock
    private CronitorPinger cronitorPinger;

    private String monitorCode = "d3x0c1";

    @Test
    public void can_start_monitor_with_minimal_requirements() throws Exception {

        client.run(monitorCode);
        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", null, null);
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorCode, "customRunMessage");
        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", null, "customRunMessage");
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorCode);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", null, null);
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorCode, "customCompleteMessage");
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", null, "customCompleteMessage");
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorCode);
        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", null, null);
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorCode, "customFailMessage");
        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", null, "customFailMessage");
    }

    @Test
    public void can_pause_monitor() throws Exception {

        client.pause(monitorCode, 5);
        verify(cronitorPinger).pause(monitorCode, 5, null);
    }

    @Test
    public void can_unpause_monitor() throws Exception {

        client.unpause(monitorCode);
        verify(cronitorPinger).pause(monitorCode, 0, null);
    }
}
