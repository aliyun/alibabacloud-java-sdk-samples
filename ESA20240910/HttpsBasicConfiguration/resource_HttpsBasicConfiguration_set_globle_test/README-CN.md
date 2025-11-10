#### 项目简介
该项目是在阿里云产品ESA（版本：2024-09-10）服务的 HttpsBasicConfiguration 资源下「创建、更新和删除 HTTPS 基本配置」的完整工程示例。此示例向您展示如何：
- 创建一个 HTTPS 基本配置。
- 更新已创建的 HTTPS 基本配置。
- 删除已创建的 HTTPS 基本配置。

#### 注意事项
- **操作费用**：
  - 运行示例代码可能对当前账号发起线上资源操作产生费用，请小心操作！

- **依赖关系**：
  - 资源【HTTPS 基本配置】依赖于资源【站点】，因此在创建 HTTPS 基本配置之前必须先成功创建站点。
  - 资源【站点】依赖于资源【套餐】，因此在创建站点之前需要先成功购买套餐。

- **异步操作**：
  - 新购套餐、创建站点均为异步操作，需等待其状态更新为指定状态后再进行下一步操作。

#### 工作流程
1. **初始化客户端**
   - 要运行此示例，您必须首先配置您的凭证，如[凭证配置](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-access-credentials)中所述。创建一个客户端实例。

2. **新购套餐**
   - 调用 [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) 接口购买一个新的资源套餐，并等待其状态变为“running”。

3. **创建站点**
   - 在成功购买套餐后，调用 [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) 接口创建一个新的站点，并等待其状态变为“pending”。

4. **创建 HTTPS 基本配置**
   - 在站点创建成功后，调用 [CreateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/CreateHttpsBasicConfiguration) 接口创建一个 HTTPS 基本配置。

5. **更新 HTTPS 基本配置**
   - 调用 [UpdateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/UpdateHttpsBasicConfiguration) 接口更新已创建的 HTTPS 基本配置。

6. **删除 HTTPS 基本配置**
   - 最后，调用 [DeleteHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/DeleteHttpsBasicConfiguration) 接口删除之前创建的 HTTPS 基本配置，完成整个生命周期管理。

#### 执行步骤
- *最低要求Java 8*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### 更多示例
更多示例请查看 https://github.com/aliyun/alibabacloud-java-sdk-samples