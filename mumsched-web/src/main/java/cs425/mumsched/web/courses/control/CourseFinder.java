
package cs425.mumsched.web.courses.control;

import cs425.mumsched.web.courses.entity.Course;
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
public class CourseFinder {

    @Autowired
    private SessionFactory sessionFactory;

    public CourseFinder() {
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findAll() {
        try {
            return sessionFactory.getCurrentSession().getNamedQuery(Course.FIND_ALL).list();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Course findByCourseCode(String courseCode) {
        try {
            Course course = (Course) sessionFactory.getCurrentSession().getNamedQuery(Course.FIND_BY_COURSE_CODE).setParameter("code", courseCode).uniqueResult();
            System.out.println("Course" + course.getCourseCode());
            return course;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findByCourseType(String courseType) {
        try {
            List<Course> courses = sessionFactory.getCurrentSession().getNamedQuery(Course.FIND_BY_COURSE_TYPE).setParameter("courseType", courseType).list();
            return courses;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
