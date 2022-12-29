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
public class Plan {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private String id;
    /**
     *
     */
    @ONodeAttr(name = "vcpu_count")
    private int vcpuCount;
    /**
     *
     */
    @ONodeAttr(name = "ram")
    private int ram;
    /**
     *
     */
    @ONodeAttr(name = "disk")
    private int disk;
    /**
     *
     */
    @ONodeAttr(name = "disk_count")
    private int diskCount;
    /**
     *
     */
    @ONodeAttr(name = "bandwidth")
    private int bandwidth;
    /**
     *
     */
    @ONodeAttr(name = "monthly_cost")
    private int monthlyCost;
    /**
     *
     */
    @ONodeAttr(name = "type")
    private String type;
    /**
     *
     */
    @ONodeAttr(name = "locations")
    private List<String> locations;
}