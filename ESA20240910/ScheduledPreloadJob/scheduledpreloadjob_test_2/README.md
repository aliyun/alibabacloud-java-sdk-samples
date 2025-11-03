#### Project Overview
This project is a complete engineering example for creating and deleting preload tasks under the ScheduledPreloadJob resource of Alibaba Cloud's ESA (version: 2024-09-10) service. This example demonstrates how to:
- Create and delete preload tasks.
- Manage dependent resources, including packages and sites.

#### Important Notes
- **Operation Costs**:
  - Running the sample code may incur costs due to online resource operations on your current account. Please operate with caution!

- **Dependencies**:
  - The resource [preload task] depends on the resource [site], so you must successfully create a site before creating a preload task.
  - The resource [site] depends on the resource [package], so you need to successfully purchase a package before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new package and creating a site are both asynchronous operations. You need to wait for their status to update to the specified state before proceeding to the next step.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Package**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource package and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing the package, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Create Preload Task**
   - After the site is successfully created, call the [CreateScheduledPreloadJob](https://api.aliyun.com/api/ESA/2024-09-10/CreateScheduledPreloadJob) interface to create a new preload task.

5. **Delete Preload Task**
   - Finally, call the [DeleteScheduledPreloadJob](https://api.aliyun.com/api/ESA/2024-09-10/DeleteScheduledPreloadJob) interface to delete the previously created preload task, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples