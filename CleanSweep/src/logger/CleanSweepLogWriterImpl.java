package logger;

import java.io.*;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Adam on 10/9/2016.
 */
public class CleanSweepLogWriterImpl implements Loggable{
    private Writer log;

    //Create file or Open existing file and never overwrite
    protected CleanSweepLogWriterImpl(){
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH)+1;
        int day = date.get(Calendar.DAY_OF_MONTH);

        String dateOfLog="";
        if(day<10) {
            dateOfLog += year + "" + month + "0" + day;
        }
        else
        {
            dateOfLog += year + "" + month + "" + day;
        }

        try {
            log = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("CleanSweepLog_" + dateOfLog + ".txt", true), "utf-8"));
        }
        catch(IOException ioe){
            System.out.println("Issue Creating Log Object/File");
            ioe.printStackTrace();
        }
    }

    //write to the file
    public void writeToLog(String message){
        Date date = new Date();
        String logDate = date.toString();
        try {
            log.write(logDate + ": " + message +"\r\n");
            log.flush();
        }
        catch(IOException ioe){
            System.out.println("Issue Writing to Log Object/File");
            ioe.printStackTrace();
        }
    }

    //Close the file
    public void closeLog(){
        try {
            log.close();
        }
        catch(IOException ioe){
            System.out.println("Issue Closing Log Object/File");
            ioe.printStackTrace();
        }
    }
}
