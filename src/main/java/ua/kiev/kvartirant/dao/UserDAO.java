package ua.kiev.kvartirant.dao;


import ua.kiev.kvartirant.model.User;

public interface UserDAO {
    User findUserByUserName(String username);
    void saveUser(User user);
}
