package main.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static Connection connection = null;
    public Connection createConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("DatabaseDriver is OK!");
        } catch (Exception ex) {
            System.out.println("DataBaseDriver connection FAILED!");
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kables?user=root&password=322022&useSSL=false");
            System.out.println("Connection is OK!");
            } catch (Exception ex) {
            System.out.println("DATABASE Connection FAILED!");
        }
        return connection;
    }
  }
