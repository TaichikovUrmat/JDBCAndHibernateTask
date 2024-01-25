package peaksoft.util;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
   private static final String url = "jdbc:postgresql://localhost:5432/postgres";
   private static final String username = "postgres";
   private static final String  password = "Urmat0204";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Application is successfully ta database!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
