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
public class BillingHistory {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     *
     */
    @ONodeAttr(name = "date")
    private LocalDateTime date;
    /**
     *
     */
    @ONodeAttr(name = "type")
    private String type;
    /**
     *
     */
    @ONodeAttr(name = "description")
    private String description;
    /**
     *
     */
    @ONodeAttr(name = "amount")
    private Double amount;
    /**
     *
     */
    @ONodeAttr(name = "balance")
    private Double balance;
}