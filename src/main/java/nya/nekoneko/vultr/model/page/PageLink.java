package nya.nekoneko.vultr.model.page;

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
public class PageLink {
    /**
     *
     */
    @ONodeAttr(name = "next")
    private String next;
    /**
     *
     */
    @ONodeAttr(name = "prev")
    private String prev;
}
