package cs425.mumsched.web.sections.control;

import cs425.mumsched.web.sections.entity.Section;
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
public class SectionFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Section findBySectionId(int sectionId) {
        Section section = new Section();
        try {
            section = (Section) this.sessionFactory.getCurrentSession().get(Section.class, sectionId);

        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return section;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
