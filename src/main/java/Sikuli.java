import org.sikuli.basics.Debug;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

/**
 * Created by schubuk on 30.10.2017.
 */
public class Sikuli {
    @Test
    public void sikuli() throws FindFailed, InterruptedException {
        /************RESOURCES***************/
        //https://www.programcreek.com/java-api-examples/index.php?api=org.sikuli.basics.Debug
        //https://raiman.github.io/SikuliX-2014/javadocs/org/sikuli/basics/Debug.html#getDebugLevel--
        /****************SIKULI LOGS***************/
        int level = 3;
        Debug.setDebugLevel(level);
        /******************************************/

        System.out.println("openCXphone");
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        //Thread.sleep(10000);
        Screen screen = new Screen();
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
    }
}
