package cs425.mumsched.web.blocks.entity;

import cs425.mumsched.web.entries.entity.Entry;
import cs425.mumsched.web.sections.entity.Section;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bikesh
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Block.FIND_BY_ENTRYID, query = "SELECT b from Block b where b.entry.entryCode=:entryCode"),
    @NamedQuery(name = Block.FIND_BY_BLOCKID, query = "SELECT b from Block b where b.blockId=:blockId")
})
public class Block implements Serializable {

    private static final String DOMAIN_PREFIX = "cs425.mumsched.web.blocks.entity.Block";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_ENTRYID = DOMAIN_PREFIX + "FIND_BY_ENTRYID";
    public static final String FIND_BY_BLOCKID = DOMAIN_PREFIX + "FIND_BY_BLOCKID";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blockId;

    @Column(name = "block_name")
    private String blockName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Block_Section",
            joinColumns = @JoinColumn(name = "block_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    @ManyToOne
    private Entry entry;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "is_active", columnDefinition = "BIT", length = 1)
    private boolean isActive;

    @Column(name = "status")
    private String status;

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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
