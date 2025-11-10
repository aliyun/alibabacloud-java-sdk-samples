#### Project Overview
This project is a complete engineering example for "creating, updating, and deleting HTTPS basic configurations" under the `HttpsBasicConfiguration` resource of the Alibaba Cloud product ESA (version: 2024-09-10). This example demonstrates how to:
- Create, update, and delete HTTPS basic configurations.

#### Precautions
- **Operational Costs**:
  - Running the sample code may incur charges on your current account due to online resource operations. Please proceed with caution!

- **Dependencies**:
  - The resource [HTTPS Basic Configuration] depends on the [Site] resource, so you must successfully create a site before creating an HTTPS basic configuration.
  - The [Site] resource depends on the [Plan] resource, so you need to purchase a plan successfully before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan and creating a site are both asynchronous operations. You need to wait for their status to update to the specified state before proceeding to the next step.

#### Workflow
1. **Initialize the Client**
   - To run this example, you must first configure your credentials as described in the [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.
   
2. **Purchase a Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource plan and wait for its status to change to "running".

3. **Create a Site**
   - After successfully purchasing a plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Create HTTPS Basic Configuration**
   - After the site is successfully created, call the [CreateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/CreateHttpsBasicConfiguration) interface to create a new HTTPS basic configuration.

5. **Update HTTPS Basic Configuration**
   - Call the [UpdateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/UpdateHttpsBasicConfiguration) interface to update the created HTTPS basic configuration.

6. **Delete HTTPS Basic Configuration**
   - Finally, call the [DeleteHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/DeleteHttpsBasicConfiguration) interface to delete the previously created HTTPS basic configuration, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples