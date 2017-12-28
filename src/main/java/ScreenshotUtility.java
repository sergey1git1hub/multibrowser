

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by SChubuk on 28.12.2017.
 */
public class ScreenshotUtility {
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
            System.out.println("Screenshot taken.");
        } catch(Exception e){
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
}