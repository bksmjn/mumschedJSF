/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.blocks.entity;

import cs425.mumsched.web.entries.entity.Entry;
import cs425.mumsched.web.sections.entity.Section;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author bikesh
 */
@Entity
@NamedQueries({
    @NamedQuery(name =Block.FIND_BY_ENTRYID, query = "SELECT b from Block b where b.entry.entryCode=:entryCode")
})
public class Block implements Serializable {

    private static final String DOMAIN_PREFIX = "cs425.mumsched.web.blocks.entity.Block";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_ENTRYID = DOMAIN_PREFIX + "FIND_BY_ENTRYID";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blockId;

    @Column(name = "block_name")
    private String blockName;

    private transient List<Section> sections;

    @ManyToOne
    private Entry entry;

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

}
