package nya.nekoneko.vultr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.time.LocalDateTime;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VultrBillingInvoiceItem {
    /**
     *
     */
    @ONodeAttr(name = "description")
    private String description;
    /**
     *
     */
    @ONodeAttr(name = "product")
    private String product;
    /**
     *
     */
    @ONodeAttr(name = "start_date")
    private LocalDateTime startDate;
    /**
     *
     */
    @ONodeAttr(name = "end_date")
    private LocalDateTime endDate;
    /**
     *
     */
    @ONodeAttr(name = "units")
    private Integer units;
    /**
     *
     */
    @ONodeAttr(name = "unit_type")
    private String unitType;
    /**
     *
     */
    @ONodeAttr(name = "unit_price")
    private Double unitPrice;
    /**
     *
     */
    @ONodeAttr(name = "total")
    private Double total;
}