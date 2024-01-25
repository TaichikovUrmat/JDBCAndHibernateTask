package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }
    public void createUsersTable() {

        String query = "create table if not exists users("+
                "id serial primary key,"+
                "name varchar,"+
                "last_name varchar,"+
                "age int)";
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Koshuldu ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "drop table users;";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
            Statement.close();
            System.out.println("group remotely ");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, int age) {
    String query = """
            insert into users(name,last_name,age)
            values(?,?,?);
            """;
        int execute = 0;
        try( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            execute = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
     String query = "delete from users where id = ?";
     try {
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         preparedStatement.setLong(1,id);

         int affectedRows = preparedStatement.executeUpdate();
         if (affectedRows>0){
             System.out.println("User with ID " + id + " removed successfully");
         }else {
             System.out.println("Not user found with Id " +id);
         }
    }catch (SQLException e){
         System.out.println(e.getMessage());
     }
    }


    public List<User> getAllUsers() {

    List<User> userList = new ArrayList<>();

    String query = "select * from users ;";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user=new User();
        while (resultSet.next()){
             user.setId(resultSet.getLong("id"));
             user.setName(resultSet.getString("name"));
             user.setLastName(resultSet.getString("last_name"));
             user.setAge(resultSet.getByte("age"));
             userList.add(user);

        }
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
        return userList;
    }


    public void cleanUsersTable() {
       String query = "delete from users;";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.executeUpdate();
           preparedStatement.close();
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
}