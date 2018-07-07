package io.seetest.automation;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;
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
    protected String IOS_CLOUD_APPLICATION_NAME = null;
    protected String ANDROID_CLOUD_APPLICATION_NAME = null;


    private static final String GRID_DOMAIN = "cloud.seetest.io";


    protected static final int RESERVATION_TIME_IN_MINUTES = 10;
    protected static final int TIMEOUT_WAIT_FOR_DEVICE_IN_MILLES = 50000;
    protected static final String REPORTS_LOCAL_DIRECTORY = "SeeTestProject";
    protected  String projectBaseDirectory = null;

    protected Client client = null;
    protected GridClient gridClient = null;

    @BeforeTest
    public void setUpTestSuite() {
        initProperties();
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

    private void initProperties() {

        String pathToProperties = Objects.requireNonNull(this.getClass().getClassLoader().getResource("seetest.properties")).getFile();
        Properties properties = new Properties();
        try (FileReader fr = new FileReader(pathToProperties)) {
            properties.load(fr);
        } catch (FileNotFoundException e) {
            log.warn("didn't fine a file for properties initProperties", pathToProperties, e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accessKey = String.valueOf(properties.get("access.key"));

        if (accessKey.length() < 10) {
            log.error("Access key must be set in the seetest.properties file", accessKey);
            log.info("To get access get to to https://cloud.seetest.io or learn at https://docs.seetest.io/display/SEET/Execute+Tests+on+SeeTest+-+Obtaining+Access+Key", accessKey);
            throw new RuntimeException("Access key invalid : accessKey - " + accessKey);
        }

        IOS_CLOUD_APPLICATION_NAME = "cloud:"+ String.valueOf(properties.get("ios.app.name").toString());
        ANDROID_CLOUD_APPLICATION_NAME = "cloud:" + String.valueOf(properties.get("android.app.name"));
        projectBaseDirectory = "cloud:" + String.valueOf(properties.get("project.base.directory"));

    }
}
