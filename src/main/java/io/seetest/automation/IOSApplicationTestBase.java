package io.seetest.automation;

import org.testng.ITestContext;
import org.testng.annotations.*;

public class IOSApplicationTestBase extends TestBase
{

    private static final String IOS_CLOUD_APPLICATION_NAME = "cloud:com.experitest.ExperiBank";
    @Parameters("deviceQuery")
    @BeforeClass
    public void setUpTestClass(@Optional("@os='ios'") String deviceQuery, ITestContext testContext) {

        String testName = this.getClass().getName();

        client = gridClient.lockDeviceForExecution(testContext.getName(), deviceQuery,
                RESERVATION_TIME_IN_MINUTES, TIMEOUT_WAIT_FOR_DEVICE_IN_MILLES);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", REPORTS_LOCAL_DIRECTORY, testName);

        client.install(IOS_CLOUD_APPLICATION_NAME, false, false);
        client.launch(IOS_CLOUD_APPLICATION_NAME, false, true);

    }

    @Test
    public void testLogin() {
        client.elementSendText("default","Username",0,"company");
        client.elementSendText("default","Password",0,"company");
        client.click("default","Login",0,1);
    }
}
