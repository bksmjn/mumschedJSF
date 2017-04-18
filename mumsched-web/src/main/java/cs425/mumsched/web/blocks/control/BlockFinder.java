package cs425.mumsched.web.blocks.control;

import cs425.mumsched.web.blocks.entity.Block;
import java.util.ArrayList;
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
public class BlockFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Block> findBlocksByEntryCode(String entryCode) {
        List<Block> blocks = new ArrayList<>();
        try {
            blocks = this.sessionFactory.getCurrentSession().getNamedQuery(Block.FIND_BY_ENTRYID).setParameter("entryCode", entryCode).list();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return blocks;
    }

}
