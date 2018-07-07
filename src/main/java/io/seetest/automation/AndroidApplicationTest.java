package io.seetest.automation;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * seetest-java-example
 * Created by tom.ben-simhon on 7/7/2018.
 */
public class AndroidApplicationTest extends TestBase {

    @Parameters("deviceQuery")
    @BeforeClass
    public void setUpTestClass(@Optional("@os='android'") String deviceQuery, ITestContext testContext) {

        String testName = this.getClass().getName();

        client = gridClient.lockDeviceForExecution(testContext.getName(), deviceQuery,
                RESERVATION_TIME_IN_MINUTES, TIMEOUT_WAIT_FOR_DEVICE_IN_MILLES);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", REPORTS_LOCAL_DIRECTORY, testName);

        client.install(ANDROID_CLOUD_APPLICATION_NAME, false, false);
        client.launch(ANDROID_CLOUD_APPLICATION_NAME, false, true);

    }



    @Test
    public void testLogin() {
        client.elementSendText("default", "Username", 0, "company");
        client.elementSendText("default", "Password", 0, "company");
        client.click("default", "Login", 0, 1);
    }

}
