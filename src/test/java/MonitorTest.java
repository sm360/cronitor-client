import com.sm360.cronitor.client.CronitorClient;
import com.sm360.cronitor.client.Monitor;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore("Manual tests")
public class MonitorTest {

    private CronitorClient client = new CronitorClient("56982a2cc95641c4b929459d95c39029");

    @Test
    public void cronitorRunTest() throws IOException {

        client.getMonitor("zkzG99").run();
    }

    @Test
    public void cronitorPauseTest() throws IOException {

        Monitor monitor = client.getMonitor("zkzG99");
        monitor.run();
        monitor.pause(1);
    }

    @Test
    public void cronitorCompleteTest() throws IOException {

        Monitor monitor = client.getMonitor("zkzG99");
        monitor.run();
        monitor.complete();
    }

    @Test
    public void cronitorFailTest() throws IOException {

        Monitor monitor = client.getMonitor("zkzG99");
        monitor.run();
        monitor.fail();
    }
}