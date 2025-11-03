#### Project Overview
This project provides a complete engineering example for creating and deleting scheduled preload jobs under the ScheduledPreloadJob resource of Alibaba Cloud's ESA (version: 2024-09-10) service. This example demonstrates how to:
- Create and delete scheduled preload jobs.

#### Important Notes
- **Operation Costs**:
  - Running the sample code may incur costs on the current account due to online resource operations. Please proceed with caution!

- **Dependencies**:
  - The resource [Scheduled Preload Job] depends on the [Site] resource, so you must successfully create a site before creating a scheduled preload job.
  - The [Site] resource depends on the [Plan] resource, so you need to successfully purchase a plan before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan and creating a site are both asynchronous operations. You need to wait for their status to update to the specified state before proceeding to the next step.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource plan and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing a plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Create Scheduled Preload Job**
   - After the site is successfully created, call the [CreateScheduledPreloadJob](https://api.aliyun.com/api/ESA/2024-09-10/CreateScheduledPreloadJob) interface to create a scheduled preload job.

5. **Delete Scheduled Preload Job**
   - Finally, call the [DeleteScheduledPreloadJob](https://api.aliyun.com/api/ESA/2024-09-10/DeleteScheduledPreloadJob) interface to delete the previously created scheduled preload job, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples