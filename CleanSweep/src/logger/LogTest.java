package logger;

import java.util.Calendar;

/**
 * Created by Adam on 10/14/2016.
 */
public class LogTest {
    public static void main(String[] args) throws Exception{
        //Test writer
        LogService logService = LogService.getInstance();
        logService.writeLineToLog("Test Line");
        logService.writeLineToLog("Second Test Line");
        logService.writeLineToLog("Third Test Line");
        logService.closeLog();

        //Test writer after closing
        logService.writeLineToLog("First Test Line After Closing");
        logService.writeLineToLog("Second Test Line After Closing");
        logService.writeLineToLog("Third Test Line After Closing");
        logService.closeLog();

        //Todays date in formatted string
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR)+1;
        int month = date.get(Calendar.YEAR);
        int day = date.get(Calendar.YEAR);

        String dateOfLog = year+""+month+""+day;
        //Test Reader
        logService.readWholeLog("");
        logService.readWholeLog(dateOfLog);
        logService.readLogFromSpecifiedLine("",3);
        logService.readLogFromSpecifiedLine(dateOfLog,5);
        //Should say line does not exist
        logService.readLogFromSpecifiedLine("",28);

        //Checks logic to make sure file will be closed before it is read when it was being written too
        logService.writeLineToLog("Test to see if it will close file before reading it");
        logService.readWholeLog("");

    }
}
