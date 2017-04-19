/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.entries.entity;

import cs425.mumsched.web.blocks.entity.Block;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author bikesh
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Entry.FIND_BY_CODE, query = "SELECT e from Entry e where e.entryCode=:entryCode"),
    @NamedQuery(name = Entry.FIND_ALL, query = "SELECT e FROM Entry e where e.isActive=1")
})
public class Entry implements Serializable {

    private static final String DOMAIN_PREFIX = "cs425.mumsched.web.entries.entity.Entry";
    public static final String FIND_BY_CODE = DOMAIN_PREFIX + "FIND_BY_CODE";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int entryId;

    @Column(name = "entry_code")
    private String entryCode;

    @Column(name = "entry_name")
    private String entryName;

    @Column(name = "number_of_FPPs")
    private int noOfFPP;

    @Column(name = "number_of_MPPs")
    private int noOfMPP;

    @Column(name = "number_of_USResidents")
    private int noOfUSRes;

    @OneToMany(mappedBy = "entry")
    private List<Block> blocks;

    private boolean isActive=true;

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public int getNoOfFPP() {
        return noOfFPP;
    }

    public void setNoOfFPP(int noOfFPP) {
        this.noOfFPP = noOfFPP;
    }

    public int getNoOfMPP() {
        return noOfMPP;
    }

    public void setNoOfMPP(int noOfMPP) {
        this.noOfMPP = noOfMPP;
    }

    public int getNoOfUSRes() {
        return noOfUSRes;
    }

    public void setNoOfUSRes(int noOfUSRes) {
        this.noOfUSRes = noOfUSRes;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return entryCode;
    }
    
    

}
