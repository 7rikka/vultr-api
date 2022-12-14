# vultr-api

个人用Vultr操作工具包

# 接口列表
- [ ] 实例(instance)
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