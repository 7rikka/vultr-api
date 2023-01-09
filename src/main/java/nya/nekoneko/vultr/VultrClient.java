package nya.nekoneko.vultr;

import nya.nekoneko.vultr.consts.VultrApplicationType;
import nya.nekoneko.vultr.consts.VultrPlanType;
import nya.nekoneko.vultr.model.*;
import nya.nekoneko.vultr.model.page.VultrPageMeta;
import nya.nekoneko.vultr.param.InstanceCreateParam;
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
    public VultrResult<VultrInstance> getInstanceList(InstanceQueryParam instanceQueryParam, PaginationParam paginationParam) {
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
        List<VultrInstance> vultrInstances = node.get("vultrInstances").toObjectList(VultrInstance.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrInstances, meta);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<VultrInstance> getInstanceList(InstanceQueryParam instanceQueryParam) {
        return getInstanceList(instanceQueryParam, null);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<VultrInstance> getInstanceList(PaginationParam paginationParam) {
        return getInstanceList(null, paginationParam);
    }

    /**
     * 获取实例列表
     */
    public VultrResult<VultrInstance> getInstanceList() {
        return getInstanceList(null, null);
    }

    /**
     * 获取指定实例信息
     *
     * @param instanceId 实例ID
     */
    public VultrInstance getInstance(String instanceId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/instances/" + instanceId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("instance").toObject(VultrInstance.class);
    }

    /**
     * 获取账单历史列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrBillingHistory> getBillingHistoryList(PaginationParam paginationParam) {
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
        List<VultrBillingHistory> vultrBillingHistoryList = node.get("billing_history").toObjectList(VultrBillingHistory.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrBillingHistoryList, meta);
    }

    /**
     * 获取账单历史列表
     *
     * @return
     */
    public VultrResult<VultrBillingHistory> getBillingHistoryList() {
        return getBillingHistoryList(null);
    }

    /**
     * 获取发票列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrBillingInvoice> getInvoiceList(PaginationParam paginationParam) {
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
        List<VultrBillingInvoice> vultrBillingInvoiceList = node.get("billing_invoices").toObjectList(VultrBillingInvoice.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrBillingInvoiceList, meta);
    }

    /**
     * 获取发票列表
     *
     * @return
     */
    public VultrResult<VultrBillingInvoice> getInvoiceList() {
        return getInvoiceList(null);
    }

    /**
     * 获取账户信息
     *
     * @return
     */
    public VultrAccount getAccountInfo() {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/account")
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        return node.get("account").toObject(VultrAccount.class);
    }

    /**
     * 获取发票信息
     *
     * @param invoiceId 发票id
     * @return
     */
    public VultrBillingInvoice getInvoice(int invoiceId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/billing/invoices/" + invoiceId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("billing_invoice").toObject(VultrBillingInvoice.class);
    }

    /**
     * 获取发票子项
     *
     * @param invoiceId 发票id
     * @return
     */
    public VultrResult<VultrBillingInvoiceItem> getInvoiceItem(int invoiceId) {
        return getInvoiceItem(invoiceId, null);
    }

    /**
     * 获取发票子项
     *
     * @param invoiceId       发票id
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrBillingInvoiceItem> getInvoiceItem(int invoiceId, PaginationParam paginationParam) {
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
        List<VultrBillingInvoiceItem> vultrBillingInvoiceItemList = node.get("invoice_items").toObjectList(VultrBillingInvoiceItem.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrBillingInvoiceItemList, meta);
    }

    /**
     * 获取应用程序列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrApplication> getApplicationList(VultrApplicationType vultrApplicationType, PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/applications")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != vultrApplicationType) {
            vultrRequest.addParam("type", vultrApplicationType.value());
        }
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrApplication> applicationList = node.get("applications").toObjectList(VultrApplication.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(applicationList, meta);
    }

    /**
     * 获取应用程序列表
     *
     * @param vultrApplicationType 应用程序类型
     * @return
     */
    public VultrResult<VultrApplication> getApplicationList(VultrApplicationType vultrApplicationType) {
        return getApplicationList(vultrApplicationType, null);
    }

    /**
     * 获取应用程序列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrApplication> getApplicationList(PaginationParam paginationParam) {
        return getApplicationList(null, paginationParam);
    }

    /**
     * 获取应用程序列表
     *
     * @return
     */
    public VultrResult<VultrApplication> getApplicationList() {
        return getApplicationList(null, null);
    }

    /**
     * 获取套餐列表
     *
     * @param vultrPlanType   套餐类型
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrPlan> getPlanList(VultrPlanType vultrPlanType, PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/plans")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != vultrPlanType) {
            vultrRequest.addParam("type", vultrPlanType.value());
        }
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrPlan> applicationList = node.get("plans").toObjectList(VultrPlan.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(applicationList, meta);
    }

    /**
     * 获取套餐列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrPlan> getPlanList(PaginationParam paginationParam) {
        return getPlanList(null, paginationParam);
    }

    /**
     * 获取套餐列表
     *
     * @param vultrPlanType 套餐类型
     * @return
     */
    public VultrResult<VultrPlan> getPlanList(VultrPlanType vultrPlanType) {
        return getPlanList(vultrPlanType, null);
    }

    /**
     * 获取套餐列表
     *
     * @return
     */
    public VultrResult<VultrPlan> getPlanList() {
        return getPlanList(null, null);
    }

    /**
     * 获取操作系统列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrOS> getOSList(PaginationParam paginationParam) {
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
        List<VultrOS> vultrOSList = node.get("os").toObjectList(VultrOS.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrOSList, meta);
    }

    /**
     * 获取操作系统列表
     *
     * @return
     */
    public VultrResult<VultrOS> getOSList() {
        return getOSList(null);
    }

    /**
     * 获取地区列表
     *
     * @param paginationParam 分页信息
     * @return
     */
    public VultrResult<VultrRegion> getRegionList(PaginationParam paginationParam) {
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
        List<VultrRegion> vultrRegionList = node.get("regions").toObjectList(VultrRegion.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(vultrRegionList, meta);
    }

    /**
     * 获取地区列表
     *
     * @return
     */
    public VultrResult<VultrRegion> getRegionList() {
        return getRegionList(null);
    }

    public VultrResult<VultrISO> getISOList(PaginationParam paginationParam) {
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
        List<VultrISO> regionList = node.get("isos").toObjectList(VultrISO.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(regionList, meta);
    }

    public VultrResult<VultrISO> getISOList() {
        return getISOList(null);
    }

    public VultrISO getISO(String isoId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso/" + isoId)
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("iso").toObject(VultrISO.class);
    }

    public VultrISO createISO(String url) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso")
                .postJson(ONode.newObject().set("url", url).toJson())
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        String json = VultrCall.doCallGetString(request);
        return ONode.loadStr(json, options).get("iso").toObject(VultrISO.class);
    }

    public void deleteISO(String isoId) {
        Request request = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso/" + isoId)
                .delete()
                .header("Authorization", "Bearer " + API_KEY)
                .buildRequest();
        VultrCall.doCallGetString(request);
    }

    public VultrResult<VultrPublicISO> getPublicISOList() {
        return getPublicISOList(null);
    }

    public VultrResult<VultrPublicISO> getPublicISOList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/iso-public")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrPublicISO> regionList = node.get("public_isos").toObjectList(VultrPublicISO.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(regionList, meta);
    }

    public VultrResult<VultrMetalPlan> getBareMetalPlanList(PaginationParam paginationParam) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/plans-metal")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrMetalPlan> metalPlanList = node.get("plans_metal").toObjectList(VultrMetalPlan.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(metalPlanList, meta);
    }

    public VultrResult<VultrMetalPlan> getBareMetalPlanList() {
        return getBareMetalPlanList(null);
    }

    public List<String> getAvailablePlanInRegion(String regionId) {
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/regions/" + regionId + "/availability")
                .header("Authorization", "Bearer " + API_KEY);
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        return ONode.loadStr(result).get("available_plans").toObjectList(String.class);
    }

    public void createInstance(InstanceCreateParam instanceCreateParam) {
        //TODO
        ONode node = ONode.newObject();
        node.set("region", "nrt");
        node.set("plan", "vc2-1c-1gb");

//        https://api.vultr.com/v2/instances
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/instances")
                .postJson(null)
                .header("Authorization", "Bearer " + API_KEY);
    }

    public VultrResult<VultrSnapshot> getSnapshotList(String description, PaginationParam paginationParam) {
        //
        VultrRequest vultrRequest = VultrRequestFactory
                .getVultrRequest()
                .url("https://api.vultr.com/v2/snapshots")
                .header("Authorization", "Bearer " + API_KEY);
        if (null != description) {
            vultrRequest.addParam("description", description);
        }
        if (null != paginationParam) {
            vultrRequest.addParam("per_page", paginationParam.getPerPage());
            vultrRequest.addParam("cursor", paginationParam.getCursor());
        }
        Request request = vultrRequest.buildRequest();
        String result = VultrCall.doCallGetString(request);
        ONode node = ONode.loadStr(result, options);
        List<VultrSnapshot> snapshotList = node.get("snapshots").toObjectList(VultrSnapshot.class);
        VultrPageMeta meta = node.get("meta").toObject(VultrPageMeta.class);
        return new VultrResult<>(snapshotList, meta);
    }

    public VultrResult<VultrSnapshot> getSnapshotList(PaginationParam paginationParam) {
        return getSnapshotList(null, paginationParam);
    }

    public VultrResult<VultrSnapshot> getSnapshotList(String description) {
        return getSnapshotList(description, null);
    }

    public VultrResult<VultrSnapshot> getSnapshotList() {
        return getSnapshotList(null, null);
    }
}
