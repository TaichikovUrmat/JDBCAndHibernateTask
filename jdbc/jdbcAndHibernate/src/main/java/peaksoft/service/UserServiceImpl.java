package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
      private final UserDao userDao = new UserDaoJdbcImpl();
      private final Connection connection = Util.getConnection();
    public void createUsersTable() {
     userDao.createUsersTable();
    }
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, int age) {
    userDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
    userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
