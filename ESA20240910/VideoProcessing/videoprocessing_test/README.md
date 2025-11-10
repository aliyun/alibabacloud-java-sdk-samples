#### Project Overview
This project is a complete engineering example for "creating, updating, and deleting video processing configurations" under the VideoProcessing resource of the Alibaba Cloud product ESA (version: 2024-09-10). This example demonstrates how to:
- Create a video processing configuration.
- Update a video processing configuration.
- Delete a video processing configuration.

#### Notes
- **Operation Costs**:
  - Running the sample code may incur costs for online resource operations on the current account. Please operate with caution!

- **Dependencies**:
  - The resource [VideoProcessing] depends on the resource [Site], so you must successfully create a site before creating a video processing configuration.
  - The resource [Site] depends on the resource [RatePlanInstance], so you need to successfully purchase a plan before creating a site.

- **Asynchronous Operations**:
  - Purchasing a new plan and creating a site are asynchronous operations, and you need to wait for their status to update to the specified state before proceeding to the next step.
  - Creating, updating, and deleting video processing configurations are synchronous operations, but you need to ensure that the prerequisite resources have been successfully created.

#### Workflow
1. **Initialize Client**
   - To run this example, you must first configure your credentials as described in [Credential Configuration](https://help.aliyun.com/zh/sdk/developer-reference/v2-manage-net-access-credentials). Create a client instance.

2. **Purchase Plan**
   - Call the [PurchaseRatePlan](https://api.aliyun.com/api/ESA/2024-09-10/PurchaseRatePlan) interface to purchase a new resource plan and wait for its status to change to "running".

3. **Create Site**
   - After successfully purchasing the plan, call the [CreateSite](https://api.aliyun.com/api/ESA/2024-09-10/CreateSite) interface to create a new site and wait for its status to change to "pending".

4. **Create Video Processing Configuration**
   - After the site has been successfully created, call the [CreateVideoProcessing](https://api.aliyun.com/api/ESA/2024-09-10/CreateVideoProcessing) interface to create a video processing configuration.

5. **Update Video Processing Configuration**
   - Call the [UpdateVideoProcessing](https://api.aliyun.com/api/ESA/2024-09-10/UpdateVideoProcessing) interface to update the created video processing configuration.

6. **Delete Video Processing Configuration**
   - Finally, call the [DeleteVideoProcessing](https://api.aliyun.com/api/ESA/2024-09-10/DeleteVideoProcessing) interface to delete the previously created video processing configuration, completing the entire lifecycle management.

#### How to Run
- *Requires Java 8 or later.*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

#### More Samples
For additional examples, visit: https://github.com/aliyun/alibabacloud-java-sdk-samples