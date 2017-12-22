import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

/**
 * Created by schubuk on 30.10.2017.
 */
public class Log4j {
    @Test
    public void selenium(){

        /***********SELENIUM LOGS**************/
        System.setProperty("webdriver.ie.driver.loglevel","TRACE");
        /****************************************/
        System.setProperty("webdriver.ie.driver", "C:/iedriver32/IEDriverServer.exe");
        //System.setProperty("webdriver.load.strategy", "unstable");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);
        //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://172.21.24.109:8087/gbpowerdialer");
        driver.quit();
    }
}
