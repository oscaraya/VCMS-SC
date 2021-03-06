package sg.edu.nus.iss.vmcs.util.audit;

import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.vmcs.system.Environment;

import static org.junit.Assert.assertTrue;

public class TransferAllCashLoggerControllerTest {
    private String propertyFilename = System.getProperty("propertyFilename");

    @Before
    public void setup() throws Exception {
        Environment.initialize(propertyFilename);

    }

    @Test
    public void testIsEnabled() throws Exception {

        LoggerController transferAllCashLoggerController = new TransferAllCashLoggerController();
        assertTrue(transferAllCashLoggerController.isEnabled());
    }

}