package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.blocks.control.BlockFinder;
import cs425.mumsched.web.blocks.entity.Block;
import cs425.mumsched.web.sections.entity.Section;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
@Lazy
public class BlockEditBean implements Serializable {

    private Block block;
    private List<Section> sections;
    private String blockId;

    private String sectionId;

    @Autowired
    private BlockFinder blockFinder;

    @PostConstruct
    private void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        this.blockId = this.parameterForEdit(fc);
        System.out.println("BLOCKID" + this.blockId);
        this.block = this.blockFinder.findBlockByBlockId(Integer.parseInt(blockId));
        this.sections = this.block.getSections();
    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("blockId");
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

}
