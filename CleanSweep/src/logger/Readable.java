package logger;

/**
 * Created by Adam on 10/10/2016.
 */
public interface Readable {
    String readWholeFile();
    String readFromSpecifiedLine(int lineNumber);
    void closeLog();
}
