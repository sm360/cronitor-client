import com.sm360.cronitor.client.CronitorClient;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Manual tests")
public class MonitorTest {

    private CronitorClient client = new CronitorClient("56982a2cc95641c4b929459d95c39029");

    @Test
    public void cronitorRunTest() throws Exception {

        client.getMonitor("F80Gdl").run("messageRun");
    }

    @Test
    public void cronitorPauseTest() throws Exception {

        client.getMonitor("F80Gdl").pause(1);
    }

    @Test
    public void cronitorUnpauseTest() throws Exception {

        client.getMonitor("F80Gdl").unpause();
    }

    @Test
    public void cronitorCompleteTest() throws Exception {

        client.getMonitor("F80Gdl").complete("messageComplete");
    }

    @Test
    public void cronitorFailTest() throws Exception {

        client.getMonitor("F80Gdl").fail("messageFail");
    }
}
