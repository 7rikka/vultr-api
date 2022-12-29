package nya.nekoneko.vultr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    /**
     *
     */
    @ONodeAttr(name = "balance")
    private Double balance;
    /**
     *
     */
    @ONodeAttr(name = "pending_charges")
    private Double pendingCharges;
    /**
     *
     */
    @ONodeAttr(name = "name")
    private String name;
    /**
     *
     */
    @ONodeAttr(name = "email")
    private String email;
    /**
     *
     */
    @ONodeAttr(name = "acls")
    private List<String> acls;
    /**
     *
     */
    @ONodeAttr(name = "last_payment_date")
    private LocalDateTime lastPaymentDate;
    /**
     *
     */
    @ONodeAttr(name = "last_payment_amount")
    private Double lastPaymentAmount;
}