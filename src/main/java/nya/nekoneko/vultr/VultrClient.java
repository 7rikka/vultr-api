package nya.nekoneko.vultr;

import nya.nekoneko.vultr.model.Instance;
import nya.nekoneko.vultr.param.InstanceQueryParam;
import nya.nekoneko.vultr.param.PaginationParam;
import okhttp3.Request;

/**
 * @author Ho
 */
public class VultrClient {
    private final String API_KEY;

    public VultrClient(String apiKey) {
        API_KEY = apiKey;
    }

    /**
     * 获取实例列表
     */
    public void getInstanceList(InstanceQueryParam instanceQueryParam, PaginationParam paginationParam) {

    }

    /**
     * 获取实例列表
     */
    public void getInstanceList(InstanceQueryParam instanceQueryParam) {
        getInstanceList(instanceQueryParam, null);
    }

    /**
     * 获取实例列表
     */
    public void getInstanceList(PaginationParam paginationParam) {
        getInstanceList(null, paginationParam);
    }

    /**
     * 获取实例列表
     */
    public void getInstanceList() {
        getInstanceList(null, null);
    }

    /**
     * 获取指定实例信息
     *
     * @param instanceId 实例ID
     */
    public Instance getInstance(String instanceId) {
        //https://api.vultr.com/v2/instances/b7d62c72-e5ec-42ba-a625-77ae2a1b6595
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/instances/" + instanceId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String s = VultrCall.doCallGetString(request);
        //{"instance":{"id":"b7d62c72-e5ec-42ba-a625-77ae2a1b6595","os":"Ubuntu 19.10 x64","ram":1024,"disk":25,"main_ip":"149.28.222.196","vcpu_count":1,"region":"sjc","plan":"vc2-1c-1gb","date_created":"2022-12-12T08:26:18+00:00","status":"active","allowed_bandwidth":1000,"netmask_v4":"255.255.254.0","gateway_v4":"149.28.222.1","power_status":"running","server_status":"ok","v6_network":"","v6_main_ip":"","v6_network_size":0,"label":"","internal_ip":"","kvm":"https:\/\/my.vultr.com\/subs\/vps\/novnc\/api.php?data=djJ8UmczekZnaUU2Y21HTEhMVDVtMFYwaGlWbzdLVG9TSTN8rhqSIz45ncksvfLhkFUkZlDoF0ILQkG4fpx7vVrkSHien3i5Kb5vMppAxeY_qpKKKEpmQDDSfuy6yW8mqXwLmyps5bBtPsU6q4P__2Y7xVL1pfvK_QGizjdiU9yiTrEx20Iq2VDecSqv971jqQaeQbhfZGvW-LUvuf_TZMfn5UU-rU0YAPL22RSApxhfIeo","hostname":"vultr.guest","tag":"","tags":[],"os_id":365,"app_id":0,"image_id":"","firewall_group_id":"","features":[]}}
        System.out.println(s);
        return null;
    }
}
