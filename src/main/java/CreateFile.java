import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SChubuk on 22.12.2017.
 */
public class CreateFile {
    public static void main(String[] args) throws IOException {
        saveLogs("main");
    }
    public static void saveLogs(String methodName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        Date date = new Date();
        File driverLog = new File("C:\\IdeaProjectsBackup\\logs\\" + methodName + dateFormat.format(date) + ".log");
        driverLog.createNewFile();
        String text = "driverLog";
        FileWriter writer = new FileWriter(driverLog);
        writer.write(text);
        writer.close();
     /*   for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }*/
    }

}
