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
 * @author amit
 */
@Repository
public class BlockFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Block> findBlocksByEntryCode(String entryCode) {
        List<Block> blocks = new ArrayList<>();
        try {
            blocks = this.sessionFactory.getCurrentSession().getNamedQuery(Block.FIND_BY_ENTRYID).setParameter("entryCode", entryCode).list();
           // this.sessionFactory.getCurrentSession().flush();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return blocks;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Block findBlockByBlockId(int blockId) {
        Block block = new Block();
        try {
            //block = (Block) this.sessionFactory.getCurrentSession().getNamedQuery(Block.FIND_BY_BLOCKID).setParameter("blockId", blockId).uniqueResult();
            block = (Block) this.sessionFactory.getCurrentSession().get(Block.class, blockId);
          //  this.sessionFactory.getCurrentSession().flush();

        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return block;
    }

}
