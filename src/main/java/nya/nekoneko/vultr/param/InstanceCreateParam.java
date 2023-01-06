package nya.nekoneko.vultr.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 实例创建参数
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceCreateParam {
    private String region;
    private String plan;
    private String os_id;
    private String ipxe_chain_url;
    private String iso_id;
    private String script_id;
    private String snapshot_id;
    private String enable_ipv6;
    private String attach_vpc;
    private String label;
    private String sshkey_id;
    private String backups;
    private String app_id;
    private String image_id;
    private String user_data;
    private String ddos_protection;
    private String activation_email;
    private String hostname;
    private String firewall_group_id;
    private String reserved_ipv4;
    private String enable_vpc;
    private List<String> tags;
}
