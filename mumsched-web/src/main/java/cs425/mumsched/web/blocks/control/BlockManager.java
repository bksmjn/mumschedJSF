/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.blocks.control;

import cs425.mumsched.web.blocks.entity.Block;
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
public class BlockManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addBlock(Block block) {
        try {
            this.sessionFactory.getCurrentSession().save(block);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
