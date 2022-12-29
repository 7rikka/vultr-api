# vultr-api

个人用Vultr操作工具包

# 接口列表
- [X] 账户(Account)
  - [X] 获取账户信息(v2/account)
- [X] 账单(Billing)
  - [X] 获取账单历史列表(v2/billing/history)
  - [X] 获取发票列表(v2/billing/invoices)
  - [X] 获取发票信息(v2/billing/invoices/{invoiceId})
  - [X] 获取发票子项(v2/billing/invoices/{invoiceId}/items)
- [ ] 实例(Instances)
    - [X] 获取实例列表(v2/instances)
    - [X] 获取指定实例信息(v2/instances/{instanceId})

# 调用示例

```
VultrClient client = new VultrClient("YOUR API KEY");
VultrResult<Instance> instanceList = client.getInstanceList();
List<Instance> list = instanceList.getList();
for (Instance instance : list) {
    System.out.println(instance);
}
```