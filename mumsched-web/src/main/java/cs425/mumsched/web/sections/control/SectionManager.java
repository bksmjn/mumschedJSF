package cs425.mumsched.web.sections.control;

import cs425.mumsched.web.sections.entity.Section;
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
public class SectionManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSection(List<Section> sections) {
        try {
            if (sections == null) {
                return;
            }
            for (Section section : sections) {
                this.sessionFactory.getCurrentSession().update(section);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSection(Section section) {
        try {
            if (section == null) {
                return;
            }
            this.sessionFactory.getCurrentSession().saveOrUpdate(section);
        } catch (Exception ex) {
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
