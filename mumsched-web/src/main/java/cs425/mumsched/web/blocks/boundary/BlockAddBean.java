package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.blocks.control.BlockFinder;
import cs425.mumsched.web.blocks.control.BlockManager;
import cs425.mumsched.web.blocks.entity.Block;
import cs425.mumsched.web.entries.control.EntryFinder;
import cs425.mumsched.web.entries.entity.Entry;
import cs425.mumsched.web.sections.entity.Section;
import cs425.mumsched.web.utils.Messages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */
@Named
@ViewScoped
public class BlockAddBean implements Serializable {

    private Block block;
    private int blockNumber;

    private List<Section> sections;

    private String entryCode;

    @Autowired
    private BlockManager blockManager;

    @Autowired
    private BlockFinder blockFinder;

    @Autowired
    private EntryFinder entryFinder;

    @Autowired
    private Messages message;

    @PostConstruct
    private void init() {
        this.block = new Block();

    }

    public String saveButtonClickedHandler() {
        try {
            this.block.setSections(sections);
            System.out.println("SIZE SECTIONS" + sections.size());
            this.blockManager.addBlock(block);
            this.sections = new ArrayList<>();
        } catch (Exception ex) {
            this.message.addError(null, "Block", ex.getMessage());
        }
        return null;
    }

    public void generateSections() {
        this.sections=new ArrayList<>();
        Entry entry = new Entry();
        int totalFPPSections = 0;
        int totalMPPSections = 0;
        int totalElectiveSections = 0;
        try {
            if (entryCode != null) {
                entry = this.entryFinder.getEntryByCode(entryCode);
                this.block.setEntry(entry);
                if (blockNumber == 1) {
                    totalFPPSections = entry.getNoOfFPP() / 35;
                    totalMPPSections = entry.getNoOfMPP() / 35;
                } else if (blockNumber == 2) {
                    totalFPPSections = 0;
                    totalMPPSections = entry.getNoOfFPP() / 35;
                    totalElectiveSections = entry.getNoOfMPP() / 35;
                } else if (blockNumber == 3 || blockNumber == 4 || blockNumber == 5 || blockNumber == 6 || blockNumber == 7) {
                    totalFPPSections = 0;
                    totalMPPSections = 0;
                    totalElectiveSections = (entry.getNoOfFPP() + entry.getNoOfMPP()) / 35;
                } else {
                    totalFPPSections = 0;
                    totalMPPSections = 0;
                    totalElectiveSections = entry.getNoOfUSRes() / 35;
                }

                for (int i = 0; i < totalFPPSections; i++) {
                    this.sections.add(new Section(this.block, "UNASSIGNED", "FPP"));
                }

                for (int i = 0; i < totalMPPSections; i++) {
                    this.sections.add(new Section(this.block, "UNASSIGNED", "MPP"));
                }
                for (int i = 0; i < totalElectiveSections; i++) {
                    this.sections.add(new Section(this.block, "UNASSIGNED", "ELECTIVE"));
                }
            }

        } catch (Exception ex) {

        }
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
