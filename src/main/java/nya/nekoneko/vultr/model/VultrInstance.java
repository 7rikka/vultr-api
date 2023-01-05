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
public class VultrInstance {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private String id;
    /**
     *
     */
    @ONodeAttr(name = "os")
    private String os;
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
    @ONodeAttr(name = "main_ip")
    private String mainIp;
    /**
     *
     */
    @ONodeAttr(name = "vcpu_count")
    private int vcpuCount;
    /**
     *
     */
    @ONodeAttr(name = "region")
    private String region;
    /**
     *
     */
    @ONodeAttr(name = "plan")
    private String plan;
    /**
     *
     */
    @ONodeAttr(name = "date_created")
    private LocalDateTime dateCreated;
    /**
     *
     */
    @ONodeAttr(name = "status")
    private String status;
    /**
     *
     */
    @ONodeAttr(name = "allowed_bandwidth")
    private int allowedBandwidth;
    /**
     *
     */
    @ONodeAttr(name = "netmask_v4")
    private String netmaskV4;
    /**
     *
     */
    @ONodeAttr(name = "gateway_v4")
    private String gatewayV4;
    /**
     *
     */
    @ONodeAttr(name = "power_status")
    private String powerStatus;
    /**
     *
     */
    @ONodeAttr(name = "server_status")
    private String serverStatus;
    /**
     *
     */
    @ONodeAttr(name = "v6_network")
    private String v6Network;
    /**
     *
     */
    @ONodeAttr(name = "v6_main_ip")
    private String v6MainIp;
    /**
     *
     */
    @ONodeAttr(name = "v6_network_size")
    private int v6NetworkSize;
    /**
     *
     */
    @ONodeAttr(name = "label")
    private String label;
    /**
     *
     */
    @ONodeAttr(name = "internal_ip")
    private String internalIp;
    /**
     *
     */
    @ONodeAttr(name = "kvm")
    private String kvm;
    /**
     *
     */
    @ONodeAttr(name = "hostname")
    private String hostname;
    /**
     *
     */
    @ONodeAttr(name = "tag")
    private String tag;
    /**
     *
     */
    @ONodeAttr(name = "tags")
    private List<String> tags;
    /**
     *
     */
    @ONodeAttr(name = "os_id")
    private int osId;
    /**
     *
     */
    @ONodeAttr(name = "app_id")
    private int appId;
    /**
     *
     */
    @ONodeAttr(name = "image_id")
    private String imageId;
    /**
     *
     */
    @ONodeAttr(name = "firewall_group_id")
    private String firewallGroupId;
    /**
     *
     */
    @ONodeAttr(name = "features")
    private List<String> features;
}