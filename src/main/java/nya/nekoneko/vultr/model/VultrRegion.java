package nya.nekoneko.vultr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VultrRegion {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private String id;
    /**
     *
     */
    @ONodeAttr(name = "city")
    private String city;
    /**
     *
     */
    @ONodeAttr(name = "country")
    private String country;
    /**
     *
     */
    @ONodeAttr(name = "continent")
    private String continent;
    /**
     *
     */
    @ONodeAttr(name = "options")
    private List<String> options;
}