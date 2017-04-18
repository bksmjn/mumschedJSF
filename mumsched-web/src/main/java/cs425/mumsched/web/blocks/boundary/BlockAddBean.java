
package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.blocks.control.BlockFinder;
import cs425.mumsched.web.blocks.control.BlockManager;
import cs425.mumsched.web.blocks.entity.Block;
import java.io.Serializable;
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
public class BlockAddBean implements Serializable{
    
    
    private Block block;
    
    @Autowired
    private BlockManager blockManager;
    
    @Autowired
    private BlockFinder blockFinder;
    
    
    @PostConstruct
    private void init(){
        this.block=new Block();
    }
    
    
    public String saveButtonClickedHandler(){
        try{
            
        }catch(Exception ex){
            
        }
        return null;
    }
    
    
}
