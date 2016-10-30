package logger;

import java.util.Calendar;

/**
 * Created by Adam on 10/14/2016.
 */
public class LogService {
    private volatile static LogService LogManager;
    private Loggable logWriter;
    private String todaysDate;

    //One instance of log service
    public static LogService getInstance() throws Exception
    {
        synchronized(LogService.class)
        {
            if (LogManager == null)
            {
                LogManager = new LogService();
            }
        }

        return LogManager;
    }

    //Create a log writer
    private LogService()
    {
        logWriter = new CleanSweepLogWriterImpl();

        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR)+1;
        int month = date.get(Calendar.YEAR);
        int day = date.get(Calendar.YEAR);

        todaysDate = year+""+month+""+day;
    }

    //Write to log writer unless it is null in which case open file and write to it
    public void writeLineToLog(String message){
        if(logWriter!=null) {
            logWriter.writeToLog(message);
        }
        else{
            logWriter = new CleanSweepLogWriterImpl();
            logWriter.writeToLog(message);
        }
    }

    //Close file and make sure log writer is set to null so it knows not to write to a closed file
    public void closeLog(){
        if(logWriter!=null){
            logWriter.closeLog();
            logWriter = null;
        }
    }

    //Read from file
    private String readFile(String dateOfLog, int lineNumber){
        String logLines="";
        if(lineNumber==0){
            CleanSweepLogReaderImpl logReader = new CleanSweepLogReaderImpl(dateOfLog);
            logLines += logReader.readWholeFile();
            logReader.closeLog();
        }else{
            CleanSweepLogReaderImpl logReader = new CleanSweepLogReaderImpl(dateOfLog);
            logLines += logReader.readFromSpecifiedLine(lineNumber);
            logReader.closeLog();
        }
        return logLines;
    }

    //Read the whole file making sure that if the writer has the file that needs to be read from, that it is closed first
    public String readWholeLog(String dateOfLog){
        String logLines="";

        if(dateOfLog.equals(todaysDate)||dateOfLog.equals("")) {
            if(logWriter==null){
                logLines+=readFile(dateOfLog,0);
            }else{
                closeLog();
                logLines+=readFile(dateOfLog,0);
            }
        }else{
            logLines+=readFile(dateOfLog,0);
        }
        return logLines;
    }


    //Read the file from a specified line making sure that if the writer has the file that needs to be read from, that it is closed first
    public String readLogFromSpecifiedLine(String dateOfLog, int lineNumber){
        String logLines="";

        if(dateOfLog.equals(todaysDate)||dateOfLog.equals("")) {
            if(logWriter==null){
                logLines += readFile(dateOfLog,lineNumber);
            }else{
                closeLog();
                logLines += readFile(dateOfLog,lineNumber);
            }
        }else{
            readFile(dateOfLog,lineNumber);
        }

        return logLines;
    }

}
