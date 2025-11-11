#### Project Overview
This project is a complete engineering example of "Creating a Certificate" under the Certificate resource of Alibaba Cloud's ESA (version: 2024-09-10) service. This example demonstrates how to:
- Create and delete certificates.

#### Precautions
- **Operational Costs**:
  - Running the sample code may incur charges for online resource operations on your current account. Please proceed with caution!

- **Dependencies**:
  - The resource [Certificate] depends on the resource [Site], so you must successfully create a site before creating a certificate.
  - The resource [Site] depends on the resource [Plan], so you need to successfully purchase a plan before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan and creating a site are asynchronous operations, and you need to wait for their status to update to the specified state before proceeding to the next step.
  - Creating and deleting a certificate are synchronous operations, and no additional status polling is required.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource plan and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing a plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Apply for Certificate**
   - After the site is successfully created, call the [ApplyCertificate](https://api.aliyun.com/api/ESA/2024-09-10/ApplyCertificate) interface to apply for a new certificate. This interface is a synchronous operation, and no additional status polling is required.

5. **Delete Certificate**
   - Finally, call the [DeleteCertificate](https://api.aliyun.com/api/ESA/2024-09-10/DeleteCertificate) interface to delete the previously created certificate. This interface is also a synchronous operation, and no additional status polling is required.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples