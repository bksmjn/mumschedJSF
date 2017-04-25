/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.blocks.control.BlockFinder;
import cs425.mumsched.web.blocks.entity.Block;
import cs425.mumsched.web.utils.Messages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
    
    @Autowired
    private Messages message;

    public String searchButtonClickedHandler() {
        try {
            System.out.println("ENTRYCODE" + entryCode);
            this.blocks = this.blockFinder.findBlocksByEntryCode(entryCode);
            System.out.println("BLOCKLIST" + this.blocks.size() + this.entryCode);
        } catch (Exception ex) {
            message.addError(null, "Block List", ex.getMessage());
        }
        return null;
    }

    public String redirectBlockEdit(Integer blockId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("blockId", blockId);
        return "/faces/blocks/editblock.xhtml?faces-redirect=true";
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

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    
}
