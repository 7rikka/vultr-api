package nya.nekoneko.vultr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VultrBillingInvoice {
    private Integer id;
    private LocalDateTime date;
    private String description;
    private Double amount;
    private Double balance;
}