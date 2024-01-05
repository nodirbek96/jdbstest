package org.example.entity.user;

import java.util.List;

public interface UserTableCallbacks {
    boolean createUserTable();
    User insertUser(User user);
    User updateUser(User user);
    boolean deleteUser(Integer id);
    User getUser(Integer id);
    List<User> getAllUsers();
    boolean checkUser(String username,String password);
}
