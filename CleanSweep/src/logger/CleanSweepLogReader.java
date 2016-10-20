package logger;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 10/10/2016.
 */
public class CleanSweepLogReader implements Readable{
    private BufferedReader log;

    //Open specified file, date will be the differentiator
    protected CleanSweepLogReader(String dateInput){
        String dateOfLog;

        if(dateInput==null||dateInput.equals("")) {
            Calendar date = Calendar.getInstance();
            int year = date.get(Calendar.YEAR) + 1;
            int month = date.get(Calendar.YEAR);
            int day = date.get(Calendar.YEAR);
            dateOfLog = year + "" + month + "" + day;
        }else{
            dateOfLog=dateInput;
        }

        try {
            log = new BufferedReader(new FileReader("CleanSweepLog_" + dateOfLog + ".txt"));
        }
        catch(IOException ioe){
            System.out.println("Issue Opening Log Object/File");
        }
    }

    //Read whole file
    public void readWholeFile(){
        String line;

        try {
            while ((line = log.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(IOException ioe){
            System.out.println("Issue Reading Log Object/File");
            ioe.printStackTrace();
        }
    }

    //Read file from specified line
    public void readFromSpecifiedLine(int lineNumber){
        String line;
        int currentLineNumber = 0;

        try {
            while ((line = log.readLine()) != null) {
                currentLineNumber++;
                if(currentLineNumber>=lineNumber){
                    System.out.println(line);
                }
            }
            if(currentLineNumber<lineNumber){
                System.out.println("File is not "+lineNumber+" lines long");
            }
        }
        catch(IOException ioe){
            System.out.println("Issue Reading Log Object/File");
            ioe.printStackTrace();
        }

    }

    //Close file being read
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
