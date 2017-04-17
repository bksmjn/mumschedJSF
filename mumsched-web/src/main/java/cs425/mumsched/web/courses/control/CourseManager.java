package cs425.mumsched.web.courses.control;

import cs425.mumsched.web.courses.entity.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bikesh
 */
@Repository
public class CourseManager {

    @Autowired
    private SessionFactory sessionFactory;

    public CourseManager() {
        System.out.println("CourseManager");
    }

    @Transactional(propagation = Propagation.REQUIRED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCourse(Course course) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(course);
            this.sessionFactory.getCurrentSession().flush();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
