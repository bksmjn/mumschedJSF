/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.blocks.control.BlockFinder;
import cs425.mumsched.web.blocks.entity.Block;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */
@Named
@ViewScoped
public class BlockListBean implements Serializable {

    private List<Block> blocks;

    private String entryCode;

    @Autowired
    private BlockFinder blockFinder;

    public void searchButtonClickedHandler() {
        try {
            this.blocks = this.blockFinder.findBlocksByEntryCode(entryCode);
            System.out.println("BLOCKLIST"+this.blocks.size()+this.entryCode);
        } catch (Exception ex) {

        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

}
