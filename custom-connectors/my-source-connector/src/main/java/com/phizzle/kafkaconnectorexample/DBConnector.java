package com.phizzle.kafkaconnectorexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Random;

import java.lang.invoke.MethodHandles;
import java.sql.*;

class Global {
    // Database Credentials
    public static String DB_ENDPOINT = System.getProperty("DB_ENDPOINT", "localhost");
    public static String DB_NAME = System.getProperty("DB_NAME", "sample");
    public static String DB_USERNAME = System.getProperty("DB_USERNAME", "postgres");
    public static String DB_PASSWORD = System.getProperty("DB_PASSWORD", "postgres");
    public static String DB_PORT = System.getProperty("DB_PORT", "5432");
}


public class DBConnector {
    private Connection c = null;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager
                    .getConnection("jdbc:postgresql://" + Global.DB_ENDPOINT + ":" + Global.DB_PORT + "/" + Global.DB_NAME,
                            Global.DB_USERNAME, Global.DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getClass().getName() + ": " + e.getMessage());
            logger.info("Unable to connect to DB");
            System.exit(1);
        }
        logger.info("Opened database successfully");
    }

    public int getId() {
        Random rand = new Random(); //instance of random class
        int upperbound = 999999999;
        //generate random values from 0-999999999
        return rand.nextInt(upperbound);
    }

    public String getString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public String getEmail() {
        String randomString = this.getString();
        return randomString + "@gmail.com";
    }

    public String getCreatedOn() {
        return "2020-08-08";
    }

    public boolean addUser(String email) throws SQLException {
        logger.info("Adding user record");
        // TODO: These values will be replaced dynamically
        String firstName = getString();
        String lastName = getString();
        int id = getId();
        String createdOn = getCreatedOn();
        Statement stmt = null;
        try {
            stmt = this.c.createStatement();
            String sql = "insert into public.user(id, first_name, last_name, email, created_on) values(" +id+ ", '"+firstName+"', '"+ lastName +"', '"+ email +"', '"+ createdOn +"');";
            stmt.executeUpdate(sql);
            logger.info("Successfully created the user");
            return true;
        } catch (Exception e) {
            logger.error("Failed to create the user", e.getMessage());
            return false;
        }
        finally {
            // closing connections
            stmt.close();
        }
    }

    public void closeConnection() {
        try {
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
