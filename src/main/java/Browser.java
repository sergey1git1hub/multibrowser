import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;


/**
 * Created by schubuk on 30.10.2017.
 */
public class Browser {
    private static WebDriver driver;

    @Test
    public void browser() throws InterruptedException, IOException {
        if (System.getProperty("browserName").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
            /*********SETUP IEDRIVER LOGGING****************/
            System.setProperty("webdriver.chrome.verboseLogging", "false");
            /************************************************/

            DesiredCapabilities caps = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            driver = new ChromeDriver(caps);
        } else {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");

            /*********SETUP IEDRIVER LOGGING*****************/
            System.setProperty("webdriver.ie.driver.loglevel", "INFO");
            /************************************************/
            System.setProperty("webdriver.ie.driver", "C:/iedriver32/IEDriverServer.exe");

            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);

            /**********PLAY WITH CAPABILITIES*********************/
            ieCapabilities.setCapability("nativeEvents", false);
            ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
            ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
            ieCapabilities.setCapability("disable-popup-blocking", true);
            ieCapabilities.setCapability("enablePersistentHover", true);
            ieCapabilities.setCapability("ignoreZoomSetting", true);
            /***************************************************/
            driver = new InternetExplorerDriver(ieCapabilities);
            driver.manage().window().maximize();

        }
        // driver.manage().window().maximize();
        driver.get("http://172.21.24.109:8087/gbpowerdialer");

        ScreenshotUtility.captureScreenshot(driver, "Powerdialer link opened");
        /*WebElement element = driver.findElement(By.cssSelector("somejibrish"));
        element.click();*/

        Assert.assertEquals(driver.getTitle(), "gbpowerdialer");

        Thread.sleep(1000);
        WebElement username = driver.findElement(By.cssSelector("#username"));
        username.sendKeys("81016");
        ScreenshotUtility.captureScreenshot(driver, "Username entered");
        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("1");
        WebElement button_SignIn = driver.findElement(By.cssSelector("#loginModal > div > div > form > div.modal-footer > button"));
        button_SignIn.click();

        //Assert.assertTrue(false);

        Thread.sleep(5000);
    }


    public static void saveLogs(String methodName) throws IOException {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        Date date = new Date();
/*
        File driverLog = new File("video/" + methodName + dateFormat.format(date) + ".log");
*/
        File driverLog = new File("video\\" + methodName + dateFormat.format(date) + ".log");
        driverLog.getParentFile().mkdirs();
        driverLog.createNewFile();

        FileWriter writer = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            writer.write(logEntry.toString() + "\\n");
        }
        writer.close();
     /*   for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }*/
    }


    @AfterTest
    public void teardown1() throws IOException {
        saveLogs("browser");
        driver.quit();
    }

/*
    @AfterTest
    public void teardown1(ITestResult result) throws IOException {
        if(ITestResult.FAILURE ==result.getStatus()){
            ScreenshotUtility.captureScreenshot(driver, ("Test case name is " +  result.getName()));
            //System.out.println("Test.");
        }
        saveLogs("browser");
        driver.quit();
    }
*/

}
