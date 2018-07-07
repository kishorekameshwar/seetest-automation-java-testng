package io.seetest.automation;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Properties;

/**
 * seetest-java-example
 * Created by tom.ben-simhon on 7/7/2018.
 */
public class TestBase {

    private Logger log = new Log4jLoggerFactory().getLogger(this.getClass().getName());

    private static final String SEETEST_PROPERTIES_FILE_PATH = "seetest.properties";
    private static String accessKey = null;
    private static final String GRID_DOMAIN = "cloud.seetest.io";


    protected static final int RESERVATION_TIME_IN_MINUTES = 10;
    protected static final int TIMEOUT_WAIT_FOR_DEVICE_IN_MILLES = 50000;
    protected static final String REPORTS_LOCAL_DIRECTORY = "SeeTestProject";
    protected static final String projectBaseDirectory = "SeeTestProject";

    protected Client client = null;
    protected GridClient gridClient = null;

    @BeforeTest
    public void setUpTestSuite() {
        init();
        gridClient = new GridClient(accessKey, GRID_DOMAIN, 443, true);
    }

    @BeforeMethod
    public void setUpTestMethod(Method method) {
        client.startStepsGroup(method.getName());
    }

    @AfterMethod
    public void tearDownTestMethod(ITestResult testResult) {

        boolean testMethodStatus = testResult.isSuccess();
        client.report("Test Method : " + testResult.getMethod().getMethodName()
                + " Completed with status " + Boolean.toString(testMethodStatus), testMethodStatus);
        client.stopStepsGroup();

    }

    @AfterClass
    public void tearDownTestClass() {
        String reportLink = client.generateReport();
        log.info("Open link to the report : " + reportLink);
    }

    private void init() {

        String pathToProperties = Objects.requireNonNull(this.getClass().getClassLoader().getResource("seetest.properties")).getFile();
        Properties properties = new Properties();
        try (FileReader fr = new FileReader(pathToProperties)) {
            properties.load(fr);
        } catch (FileNotFoundException e) {
            log.warn("didn't fine a file for properties init", pathToProperties, e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accessKey = String.valueOf(properties.get("access.key"));
    }
}
