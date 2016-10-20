package logger;

/**
 * Created by Adam on 10/9/2016.
 */
public interface Loggable {
    void writeToLog(String message);
    void closeLog();
}
