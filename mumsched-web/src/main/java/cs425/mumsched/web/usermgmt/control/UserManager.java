package cs425.mumsched.web.usermgmt.control;

import cs425.mumsched.web.usermgmt.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bikesh
 */
@Repository
public class UserManager {

    @Autowired
    private SessionFactory sessionFactory;

    public UserManager() {
        System.out.println("UserManager");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
