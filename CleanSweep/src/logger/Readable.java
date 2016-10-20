package logger;

/**
 * Created by Adam on 10/10/2016.
 */
public interface Readable {
    void readWholeFile();
    void readFromSpecifiedLine(int lineNumber);
    void closeLog();
}
