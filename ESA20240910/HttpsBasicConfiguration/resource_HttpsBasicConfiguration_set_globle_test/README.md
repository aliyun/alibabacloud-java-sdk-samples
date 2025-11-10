#### Project Overview
This project is a complete engineering example for "creating, updating, and deleting HTTPS basic configurations" under the HttpsBasicConfiguration resource of Alibaba Cloud's ESA (version: 2024-09-10) service. This example demonstrates how to:
- Create an HTTPS basic configuration.
- Update an existing HTTPS basic configuration.
- Delete an existing HTTPS basic configuration.

#### Notes
- **Operation Costs**:
  - Running the sample code may incur costs by initiating online resource operations on your current account. Please proceed with caution!

- **Dependencies**:
  - The resource [HTTPS Basic Configuration] depends on the [Site] resource, so you must successfully create a site before creating an HTTPS basic configuration.
  - The [Site] resource depends on the [Plan] resource, so you need to successfully purchase a plan before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan and creating a site are both asynchronous operations. You must wait for their status to update to the specified state before proceeding to the next step.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in the [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) API to purchase a new resource plan and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing a plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) API to create a new site and wait for its status to change to "pending".

4. **Create HTTPS Basic Configuration**
   - After the site is successfully created, call the [CreateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/CreateHttpsBasicConfiguration) API to create an HTTPS basic configuration.

5. **Update HTTPS Basic Configuration**
   - Call the [UpdateHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/UpdateHttpsBasicConfiguration) API to update the created HTTPS basic configuration.

6. **Delete HTTPS Basic Configuration**
   - Finally, call the [DeleteHttpsBasicConfiguration](https://api.aliyun.com/api/ESA/2024-09-10/DeleteHttpsBasicConfiguration) API to delete the previously created HTTPS basic configuration, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples