package ua.kiev.kvartirant.service;


import ua.kiev.kvartirant.model.User;

/**
 * Service class for {@link ua.kiev.kvartirant.model.User}
 */

public interface UserService {

    void saveUser(User user);
    User findUserByUserName(String username);
}
