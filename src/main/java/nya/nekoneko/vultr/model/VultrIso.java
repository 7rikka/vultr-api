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
public class VultrIso {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private String id;
    /**
     *
     */
    @ONodeAttr(name = "date_created")
    private LocalDateTime dateCreated;
    /**
     *
     */
    @ONodeAttr(name = "filename")
    private String filename;
    /**
     *
     */
    @ONodeAttr(name = "size")
    private Long size;
    /**
     *
     */
    @ONodeAttr(name = "md5sum")
    private String md5sum;
    /**
     *
     */
    @ONodeAttr(name = "sha512sum")
    private String sha512sum;
    /**
     *
     */
    @ONodeAttr(name = "status")
    private String status;

}