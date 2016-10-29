package logger;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Adam on 10/29/2016.
 */
public class LogServiceTest  {
    private LogService logService;
    private String fileName;
    private String logDate;

    private String lineToCheck1;
    private String lineToCheck2;
    private String lineToCheck3;

    public LogServiceTest(){}

    @Before
    public void setUp() throws Exception {

        //Create log instance and make sure the file the log will
        //be saving to is not already there, delete it if it is
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR)+1;
        int month = date.get(Calendar.YEAR);
        int day = date.get(Calendar.YEAR);

        String todaysDate = year+""+month+""+day;
        fileName = "CleanSweepLog_"+todaysDate+".txt";

        Date dateTime = new Date();
        logDate=dateTime.toString();
        //name of file usually made is CleanSweepLog_yyyymmdd.txt

        File file = new File(fileName);
        if(file.exists()&&!file.isDirectory()){
            if(!file.delete()) {
                //If delete failed and file is there, end JUNIT test since
                //it will fail
                System.out.println("Old file cannot be deleted");
                System.exit(1);
            }
        }

        logService = LogService.getInstance();

        lineToCheck1 = logDate + ": " + "Hello Line 1!";
        logService.writeLineToLog("Hello Line 1!");
        lineToCheck2 = logDate + ": " + "Hello Line 2!";
        logService.writeLineToLog("Hello Line 2!");
        lineToCheck3 = logDate + ": " + "Hello Line 3!";
        logService.writeLineToLog("Hello Line 3!");
    }

    @After
    public void tearDown() throws Exception {
        //Close log
        if(logService!=null){
            logService.closeLog();
        }
    }


    @Test
    public void writeLineToLog() throws Exception {

        //Lines to check and see if file is equal to
        String linesToCheck = lineToCheck1+lineToCheck2+lineToCheck3;
        //Check with written method below if the file has these lines
        assertEquals(linesToCheck,readLineFromFile(0));

    }


    @Test
    public void closeLog() throws Exception {
        logService.closeLog();
        //Close file
        BufferedReader log;
        //Default to true if it closed
        Boolean fileClosed=true;

        //Try to open file, if it can open than the file must have closed earlier
        //If it fails and runs into catch, file did not close properly
        try {
            log = new BufferedReader(new FileReader(fileName));
            log.close();
        }
        catch(IOException ioe){
            System.out.println("Issue Reading Log Object/File");
            ioe.printStackTrace();
            fileClosed=false;
        }

        assertTrue(fileClosed);

    }

    @Test
    public void readWholeLog() throws Exception {
        //Checks lines written in file previously by using readWholeLog method
        String linesToCheck = lineToCheck1+lineToCheck2+lineToCheck3;
        assertEquals(linesToCheck,logService.readWholeLog(""));
    }

    @Test
    public void readLogFromSpecifiedLine() throws Exception {
        //Checks lines written in file previously by using readLogFromSpecifiedLine method
        assertEquals(lineToCheck3,logService.readLogFromSpecifiedLine("",3));

    }

    public String readLineFromFile(int lineNumber) throws Exception{
        //Use buffered reader to read in lines just written to file
        BufferedReader log;

        String line;
        int currentLineNumber = 0;

        //Save all the lines in a string object
        String totalOutput = "";

        //Read in recently written to file
        try {
            log = new BufferedReader(new FileReader(fileName));

            while ((line = log.readLine()) != null) {
                currentLineNumber++;
                if(currentLineNumber>=lineNumber){
                    System.out.println(line);
                    totalOutput+=line;
                }
            }
            if(currentLineNumber<lineNumber){
                System.out.println("File is not "+lineNumber+" lines long");
            }
            log.close();
        }
        catch(IOException ioe){
            System.out.println("Issue Reading Log Object/File");
            ioe.printStackTrace();
        }

        return totalOutput;
    }


}