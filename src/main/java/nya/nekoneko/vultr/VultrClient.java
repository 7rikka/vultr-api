package nya.nekoneko.vultr;

import nya.nekoneko.vultr.model.BillingHistory;
import nya.nekoneko.vultr.model.Instance;
import nya.nekoneko.vultr.model.page.PageMeta;
import nya.nekoneko.vultr.param.InstanceQueryParam;
import nya.nekoneko.vultr.param.PaginationParam;
import okhttp3.Request;
import org.noear.snack.ONode;
import org.noear.snack.core.Options;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ho
 */
public class VultrClient {
    private final String API_KEY;
    private final Options options = Options.def();

    public VultrClient(String apiKey) {
        API_KEY = apiKey;
        options.addDecoder(LocalDateTime.class, (node, type) -> TimeUtils.toBeijingTime(node.getRawString()));
    }

    /**
     * 获取实例列表
     */
    public VultrResult<Instance> getInstanceList(InstanceQueryParam instanceQueryParam, PaginationParam paginationParam) {
        //https://api.vultr.com/v2/instances
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/instances")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        if (null != instanceQueryParam) {
            vultrRequest.addParam("label", instanceQueryParam.getLabel());
            vultrRequest.addParam("main_ip", instanceQueryParam.getMainIp());
            vultrRequest.addParam("region", instanceQueryParam.getRegion());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Instance> instances = node.get("instances").toObjectList(Instance.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(instances, meta);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<Instance> getInstanceList(InstanceQueryParam instanceQueryParam) {
        return getInstanceList(instanceQueryParam, null);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<Instance> getInstanceList(PaginationParam paginationParam) {
        return getInstanceList(null, paginationParam);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<Instance> getInstanceList() {
        return getInstanceList(null, null);
    }

    /**
     * 获取指定实例信息
     *
     * @param instanceId 实例ID
     */
    public Instance getInstance(String instanceId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/instances/" + instanceId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("instance").toObject(Instance.class);
    }

    /**
     * 获取账单历史列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<BillingHistory> getBillingHistoryList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/billing/history")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<BillingHistory> billingHistoryList = node.get("billing_history").toObjectList(BillingHistory.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(billingHistoryList, meta);
    }

    /**
     * 获取账单历史列表
     *
     * @return
     */
    public VultrResult<BillingHistory> getBillingHistoryList() {
        return getBillingHistoryList(null);
    }
}
