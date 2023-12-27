package org.example.entity.user;

import java.util.List;

public interface UserTableCallbacks {
    boolean createUserTable();
    UserDto insertUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    boolean deleteUser(Integer id);
    UserDto getUser(Integer id);
    List<UserDto> getAllUsers();
}
