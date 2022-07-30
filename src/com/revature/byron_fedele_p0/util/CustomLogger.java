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
            writer = new FileWriter("resources/log.txt", true);
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not open connection to file. Only printing logs to console.");
        }
        this.logWriter = writer;
    }

}
