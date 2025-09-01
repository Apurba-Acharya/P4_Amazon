package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static void info(String message) {
        logger.log(Level.INFO, message);
    }

    public static void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}