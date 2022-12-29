package nya.nekoneko.vultr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     *
     */
    @ONodeAttr(name = "name")
    private String name;
    /**
     *
     */
    @ONodeAttr(name = "short_name")
    private String shortName;
    /**
     *
     */
    @ONodeAttr(name = "deploy_name")
    private String deployName;
    /**
     *
     */
    @ONodeAttr(name = "type")
    private String type;
    /**
     *
     */
    @ONodeAttr(name = "vendor")
    private String vendor;
    /**
     *
     */
    @ONodeAttr(name = "image_id")
    private String imageId;
}