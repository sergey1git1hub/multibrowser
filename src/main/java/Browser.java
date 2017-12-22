import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.basics.Debug;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.regex.Pattern;



/**
 * Created by schubuk on 30.10.2017.
 */
public class Browser {
    private static WebDriver driver;

    @Test
    public void browser() throws InterruptedException, IOException {
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
       // driver.manage().window().maximize();
        driver.get("http://172.21.24.109:8087/gbpowerdialer");

        WebElement element = driver.findElement(By.cssSelector("somejibrish"));
        element.click();

        saveLogs("browser");
        Thread.sleep(5000);

        driver.quit();
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
}
