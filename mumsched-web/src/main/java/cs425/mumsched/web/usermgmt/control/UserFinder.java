package cs425.mumsched.web.usermgmt.control;

import cs425.mumsched.web.usermgmt.entity.User;
import java.util.List;
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
public class UserFinder {

    @Autowired
    private SessionFactory sessionFactory;

    public UserFinder() {
        System.out.println("INSIDE USER FINDER");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        List<User> users=sessionFactory.getCurrentSession().getNamedQuery(User.FIND_ALL).list();
        this.sessionFactory.getCurrentSession().flush();
        return users;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findUserByEmailAddress(String emailAddress) {
        User user= (User) sessionFactory.getCurrentSession().getNamedQuery(User.FIND_BY_USERNAME).setParameter("email", emailAddress).uniqueResult();
        this.sessionFactory.getCurrentSession().flush();
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findUsersByRole(String role) {
        try {
            List<User> users = this.sessionFactory.getCurrentSession().getNamedQuery(User.FIND_BY_ROLE).setParameter("role", role).list();
            this.sessionFactory.getCurrentSession().flush();
            return users;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
