#### Project Overview
This project is a complete engineering example for "creating records" under the Record resource of Alibaba Cloud's ESA (version: 2024-09-10) service. This example demonstrates how to:
- Create, update, and delete records.

#### Precautions
- **Operational Costs**:
  - Running the sample code may incur costs for online resource operations on the current account. Please proceed with caution!

- **Dependencies**:
  - The resource [Record] depends on the resource [Site], so you must successfully create a site before creating a record.
  - The resource [Site] depends on the resource [Plan], so you need to successfully purchase a plan before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan, creating a site, creating a record, updating a record, and deleting a record are all asynchronous operations. You need to wait for their status to update to the specified state before proceeding to the next step.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource plan and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing a plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Create DNS Record**
   - After the site is successfully created, call the [CreateRecord](https://api.aliyun.com/api/ESA/2024-09-10/CreateRecord) interface to create an SMIMEA type DNS record.

5. **Update DNS Record**
   - Call the [UpdateRecord](https://api.aliyun.com/api/ESA/2024-09-10/UpdateRecord) interface to update the created DNS record until the update is successful.

6. **Delete DNS Record**
   - Finally, call the [DeleteRecord](https://api.aliyun.com/api/ESA/2024-09-10/DeleteRecord) interface to delete the previously created DNS record until the deletion is successful, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples