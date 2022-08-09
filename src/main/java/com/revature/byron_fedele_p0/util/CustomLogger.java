package com.revature.byron_fedele_p0.util;
//this imports the builtin Java classes needed for this class

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Properties;

public class CustomLogger {
    //the below are the colors for the text that will come out in terminal.
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static CustomLogger logger; //Doesn't create a logger object, just references it for later, so it is ok.
    private final boolean printToConsole; //using final since its value will not change once initialized
    private final Writer logWriter;

    private CustomLogger(boolean printToConsole) {
        Writer writer = null;
        this.printToConsole = printToConsole;
        try {
            writer = new FileWriter("src/main/resources/log.txt", true);
        } catch (IOException e) {
            //printMessageToConsole("ERROR", "Could not open connection to file. Only printing logs to console.");
        }
        this.logWriter = writer;
    }

    public static CustomLogger getLogger(boolean printToConsole) {
        // CustomLogger will not be created until it is needed
        if (logger == null) {
            logger = new CustomLogger(printToConsole);
        }
        return logger;
    }

    /* the formatMessage method returns a string with the type of log aka "level" in brackets [],
    followed by the message which is also parameter of this method, and the time that it occurred  */
    private String formatMessage(String level, String message) {
        return String.format("[%s] %s at %s", level, message, LocalDateTime.now());
    }

    //The logMessageToFile method will write the formattedMessage into resources/log.txt, has to be public to be accessed by classes in other  packages
    public void logMessageToFile(String formattedMessage) {
        if (logWriter != null) {
            try {
                logWriter.write(formattedMessage + "\n");
                logWriter.flush();
            } catch (IOException e) {
                printMessageToConsole("ERROR", "Could not write message to file");
            }
        }
    }

    // printMessageToConsole method will print the message parameter into console with type of log aka "level" with the corresponding color
    private void printMessageToConsole(String level, String message) {
        switch (level) {
            case "INFO":
                System.out.println(ANSI_GREEN + message + ANSI_RESET);//always reset at end so the last color choice does not remain.
                break;
            case "WARN":
                System.out.println(ANSI_PURPLE + message + ANSI_RESET);
                break;
            case "ERROR": // to be filled out later
            case "FATAL":
                System.out.println(ANSI_RED + message + ANSI_RESET);
                break;
        }
    }

    /*the following methods [info,warn,error,fatal] will log and print to console the activity that occurs as the program is running
    when they are called
     */
    public void info(String message, Object... extra) { // the Object... extra is in case we want to pass multiple values inside our message when we format
        String formattedMessage = formatMessage("INFO", String.format(message, extra)); // creates a string with the message provided, the extra is for a case like String.format("[%s] %s at %s", level, message, LocalDateTime.now())
        logMessageToFile(formattedMessage);//actually logs in the message to the log.txt file
        if (printToConsole)
            printMessageToConsole("INFO", formattedMessage); //if printToConsole is True, also put the same formattedMessage into console
    }

    public void warn(String message, Object... extra) {
        String formattedMessage = formatMessage("WARN", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if (printToConsole) printMessageToConsole("WARN", formattedMessage);
    }
    public void error(String message, Object... extra) {
        String formattedMessage = formatMessage("ERROR", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if (printToConsole) printMessageToConsole("ERROR", formattedMessage);
    }

    public void fatal(String message, Object... extra) {
        String formattedMessage = formatMessage("FATAL", String.format(message, extra));
        logMessageToFile(formattedMessage);
        if (printToConsole) printMessageToConsole("FATAL", formattedMessage);
    }
}
