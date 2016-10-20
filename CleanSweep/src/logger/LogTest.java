package logger;

import java.util.Calendar;

/**
 * Created by Adam on 10/14/2016.
 */
public class LogTest {
    public static void main(String[] args) throws Exception{
        //Test writer
        LogService.getInstance().writeLineToLog("Test Line");
        LogService.getInstance().writeLineToLog("Second Test Line");
        LogService.getInstance().writeLineToLog("Third Test Line");
        LogService.getInstance().closeLog();

        //Test writer after closing
        LogService.getInstance().writeLineToLog("First Test Line After Closing");
        LogService.getInstance().writeLineToLog("Second Test Line After Closing");
        LogService.getInstance().writeLineToLog("Third Test Line After Closing");
        LogService.getInstance().closeLog();

        //Todays date in formatted string
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR)+1;
        int month = date.get(Calendar.YEAR);
        int day = date.get(Calendar.YEAR);

        String dateOfLog = year+""+month+""+day;
        //Test Reader
        LogService.getInstance().readWholeLog("");
        LogService.getInstance().readWholeLog(dateOfLog);
        LogService.getInstance().readLogFromSpecifiedLine("",3);
        LogService.getInstance().readLogFromSpecifiedLine(dateOfLog,5);
        //Should say line does not exist
        LogService.getInstance().readLogFromSpecifiedLine("",28);

        //Checks logic to make sure file will be closed before it is read when it was being written too
        LogService.getInstance().writeLineToLog("Test to see if it will close file before reading it");
        LogService.getInstance().readWholeLog("");

    }
}
