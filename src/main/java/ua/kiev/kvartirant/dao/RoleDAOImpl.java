package ua.kiev.kvartirant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.kvartirant.model.Role;

import javax.persistence.EntityManager;

/**
 * Implementation of {@link RoleDAO} interface.
 * id = 1 (USER_ROLE)
 * id = 2 (ANONYMOUS_ROLE)
 * id = 3 (ADMIN_ROLE)
 */

public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Role getRoleById(long id) {
        try {
            Role role = entityManager.find(Role.class, id);
            return role;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
