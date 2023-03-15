package com.example.crms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnection {

    public Connection databaseLink;

    public Connection getConnection() throws Exception {
       String databaseName = "customer";
       String databaseUser = "root";
       String databasePassword = "sharvi";
      String url = "jdbc:mysql://localhost:3306/customer?SSL=false" + databaseName;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        catch(Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}


