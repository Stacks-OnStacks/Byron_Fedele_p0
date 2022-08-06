package com.revature.byron_fedele_p0.util;



import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    // This class OPENs connections and delivers them to our DAO, try-with-resources is very useful

    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); // eagerly instantiated singleton means it is created immediately
    private Properties props = new Properties(); // this will be used to read from DB properties

    private ConnectionFactory(){ // default no arg constructor is public, singletons don't want that
        try {
            props.load(new FileReader("src/main/resources/db.properties")); // read from db.properties
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static { // static block, just runs everything inside the {} (block) at class loading
        try{
            Class.forName("org.postgresql.Driver"); // grab the driver before anything else happens
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // getter for our instance of a ConnectionFactory
    public static ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password")); // return the connection
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}