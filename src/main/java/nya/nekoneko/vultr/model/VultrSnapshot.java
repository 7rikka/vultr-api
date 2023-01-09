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
public class VultrSnapshot {
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
    @ONodeAttr(name = "description")
    private String description;
    /**
     *
     */
    @ONodeAttr(name = "size")
    private Long size;
    /**
     *
     */
    @ONodeAttr(name = "compressed_size")
    private Long compressedSize;
    /**
     *
     */
    @ONodeAttr(name = "status")
    private String status;
    /**
     *
     */
    @ONodeAttr(name = "os_id")
    private Integer osId;
    /**
     *
     */
    @ONodeAttr(name = "app_id")
    private Integer appId;
}
