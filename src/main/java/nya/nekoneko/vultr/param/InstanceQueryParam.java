package nya.nekoneko.vultr.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实例查询参数
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceQueryParam {
    private String label;
    private String mainIp;
    private String region;
}
