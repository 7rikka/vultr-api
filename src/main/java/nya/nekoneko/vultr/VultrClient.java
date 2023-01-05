package nya.nekoneko.vultr;

import nya.nekoneko.vultr.consts.ApplicationType;
import nya.nekoneko.vultr.consts.PlanType;
import nya.nekoneko.vultr.model.*;
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

    /**
     * 获取发票列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<BillingInvoice> getInvoiceList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/billing/invoices")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<BillingInvoice> billingInvoiceList = node.get("billing_invoices").toObjectList(BillingInvoice.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(billingInvoiceList, meta);
    }

    /**
     * 获取发票列表
     *
     * @return
     */
    public VultrResult<BillingInvoice> getInvoiceList() {
        return getInvoiceList(null);
    }

    /**
     * 获取账户信息
     *
     * @return
     */
    public Account getAccountInfo() {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/account")
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        return node.get("account").toObject(Account.class);
    }

    /**
     * 获取发票信息
     *
     * @param invoiceId 发票id
     * @return
     */
    public BillingInvoice getInvoice(int invoiceId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/billing/invoices/" + invoiceId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("billing_invoice").toObject(BillingInvoice.class);
    }

    /**
     * 获取发票子项
     *
     * @param invoiceId 发票id
     * @return
     */
    public VultrResult<BillingInvoiceItem> getInvoiceItem(int invoiceId) {
        return getInvoiceItem(invoiceId, null);
    }

    /**
     * 获取发票子项
     *
     * @param invoiceId       发票id
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<BillingInvoiceItem> getInvoiceItem(int invoiceId, PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/billing/invoices/" + invoiceId + "/items")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<BillingInvoiceItem> billingInvoiceItemList = node.get("invoice_items").toObjectList(BillingInvoiceItem.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(billingInvoiceItemList, meta);
    }

    /**
     * 获取应用程序列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Application> getApplicationList(ApplicationType applicationType, PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/applications")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != applicationType) {
            vultrRequest.addParam("type", applicationType.value());
        }
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Application> applicationList = node.get("applications").toObjectList(Application.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(applicationList, meta);
    }

    /**
     * 获取应用程序列表
     *
     * @param applicationType 应用程序类型
     * @return
     */
    public VultrResult<Application> getApplicationList(ApplicationType applicationType) {
        return getApplicationList(applicationType, null);
    }

    /**
     * 获取应用程序列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Application> getApplicationList(PaginationParam paginationParam) {
        return getApplicationList(null, paginationParam);
    }

    /**
     * 获取应用程序列表
     *
     * @return
     */
    public VultrResult<Application> getApplicationList() {
        return getApplicationList(null, null);
    }

    /**
     * 获取套餐列表
     *
     * @param planType        套餐类型
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Plan> getPlanList(PlanType planType, PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/plans")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != planType) {
            vultrRequest.addParam("type", planType.value());
        }
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Plan> applicationList = node.get("plans").toObjectList(Plan.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(applicationList, meta);
    }

    /**
     * 获取套餐列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Plan> getPlanList(PaginationParam paginationParam) {
        return getPlanList(null, paginationParam);
    }

    /**
     * 获取套餐列表
     *
     * @param planType 套餐类型
     * @return
     */
    public VultrResult<Plan> getPlanList(PlanType planType) {
        return getPlanList(planType, null);
    }

    /**
     * 获取套餐列表
     *
     * @return
     */
    public VultrResult<Plan> getPlanList() {
        return getPlanList(null, null);
    }

    /**
     * 获取操作系统列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Os> getOSList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/os")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Os> osList = node.get("os").toObjectList(Os.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(osList, meta);
    }

    /**
     * 获取操作系统列表
     *
     * @return
     */
    public VultrResult<Os> getOSList() {
        return getOSList(null);
    }

    /**
     * 获取地区列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<Region> getRegionList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/regions")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Region> regionList = node.get("regions").toObjectList(Region.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(regionList, meta);
    }

    /**
     * 获取地区列表
     *
     * @return
     */
    public VultrResult<Region> getRegionList() {
        return getRegionList(null);
    }

    public VultrResult<Iso> getISOList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<Iso> regionList = node.get("isos").toObjectList(Iso.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(regionList, meta);
    }

    public VultrResult<Iso> getISOList() {
        return getISOList(null);
    }

    public Iso getISO(String isoId){
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso/" + isoId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("iso").toObject(Iso.class);
    }
    public Iso createISO(String url){
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso")
                .postJson(ONode.newObject().set("url", url).toJson())
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("iso").toObject(Iso.class);
    }

    public void deleteISO(String isoId){
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso/"+isoId)
                .delete()
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        VultrCall.doCallGetString(request);
    }

    public VultrResult<VultrPublicISO> getPublicISOList() {
        //https://api.vultr.com/v2/iso-public
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso-public")
                .header("Authorization", "Bearer " + API_KEY);
//        if (null != paginationParam) {
//            vultrRequest.addParam("per_page", paginationParam.getPerPage());
//            vultrRequest.addParam("cursor", paginationParam.getCursor());
//        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrPublicISO> regionList = node.get("public_isos").toObjectList(VultrPublicISO.class);
        PageMeta meta = node.get("meta").toObject(PageMeta.class);
        return new VultrResult<>(regionList, meta);
    }
}
