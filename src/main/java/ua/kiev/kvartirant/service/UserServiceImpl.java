package ua.kiev.kvartirant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.kvartirant.dao.RoleDAO;
import ua.kiev.kvartirant.dao.UserDAO;
import ua.kiev.kvartirant.model.Role;
import ua.kiev.kvartirant.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation  of {@link UserService} interface
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getRoleById(1l));
        user.setRoles(roles);
        userDAO.saveUser(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDAO.findUserByUserName(username);
    }
}
