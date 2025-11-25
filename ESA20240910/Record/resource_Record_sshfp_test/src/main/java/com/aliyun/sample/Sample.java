// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.sample;

import com.aliyun.tea.*;
import com.aliyun.credentials.*;
import com.aliyun.esa20240910.*;
import com.aliyun.esa20240910.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;

public class Sample {

    public Sample() throws Exception {
    }


    /**
     * <b>description</b> :
     * <p>Init Client</p>
     */
    public static com.aliyun.esa20240910.Client createESA20240910Client() throws Exception {
        Config config = new Config();
        config.credential = new com.aliyun.credentials.Client();
        // Endpoint please refer to https://api.aliyun.com/product/ESA
        config.endpoint = "esa.cn-hangzhou.aliyuncs.com";
        return new com.aliyun.esa20240910.Client(config);
    }

    public static PurchaseRatePlanResponseBody ratePlanInstSshfp(com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call PurchaseRatePlan to create resource");
        PurchaseRatePlanRequest purchaseRatePlanRequest = new PurchaseRatePlanRequest()
                .setType("NS")
                .setChargeType("PREPAY")
                .setAutoRenew(false)
                .setPeriod(1)
                .setCoverage("overseas")
                .setAutoPay(true)
                .setPlanName("high");
        PurchaseRatePlanResponse purchaseRatePlanResponse = client.purchaseRatePlan(purchaseRatePlanRequest);
        DescribeRatePlanInstanceStatusRequest describeRatePlanInstanceStatusRequest = new DescribeRatePlanInstanceStatusRequest()
                .setInstanceId(purchaseRatePlanResponse.body.instanceId);
        int currentRetry = 0;
        int delayedTime = 10000;
        int interval = 10000;

        while (currentRetry < 10) {
            try {
                int sleepTime = 0;
                if (currentRetry == 0) {
                    sleepTime = delayedTime;
                } else {
                    sleepTime = interval;
                }

                System.out.println("Polling for asynchronous results...");
                Thread.sleep(sleepTime);
            } catch (TeaException error) {
                throw new TeaException(TeaConverter.buildMap(
                    new TeaPair("message", error.message)
                ));
            }            
            DescribeRatePlanInstanceStatusResponse describeRatePlanInstanceStatusResponse = client.describeRatePlanInstanceStatus(describeRatePlanInstanceStatusRequest);
            String instanceStatus = describeRatePlanInstanceStatusResponse.body.instanceStatus;
            if (instanceStatus.equals("running")) {
                System.out.println("Call PurchaseRatePlan success, response: ");
                System.out.println(com.aliyun.teautil.Common.toJSONString(purchaseRatePlanResponse));
                return purchaseRatePlanResponse.body;
            }

            currentRetry++;
        }
        throw new TeaException(TeaConverter.buildMap(
            new TeaPair("message", "Asynchronous check failed")
        ));
    }

    public static CreateSiteResponseBody siteSshfp(PurchaseRatePlanResponseBody ratePlanInstSshfpResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateSite to create resource");
        CreateSiteRequest createSiteRequest = new CreateSiteRequest()
                .setSiteName("gositecdn.cn")
                .setInstanceId(ratePlanInstSshfpResponseBody.instanceId)
                .setCoverage("overseas")
                .setAccessType("NS");
        CreateSiteResponse createSiteResponse = client.createSite(createSiteRequest);
        GetSiteRequest getSiteRequest = new GetSiteRequest()
                .setSiteId(createSiteResponse.body.siteId);
        int currentRetry = 0;
        int delayedTime = 60000;
        int interval = 10000;

        while (currentRetry < 5) {
            try {
                int sleepTime = 0;
                if (currentRetry == 0) {
                    sleepTime = delayedTime;
                } else {
                    sleepTime = interval;
                }

                System.out.println("Polling for asynchronous results...");
                Thread.sleep(sleepTime);
            } catch (TeaException error) {
                throw new TeaException(TeaConverter.buildMap(
                    new TeaPair("message", error.message)
                ));
            }            
            GetSiteResponse getSiteResponse = client.getSite(getSiteRequest);
            String status = getSiteResponse.body.siteModel.status;
            if (status.equals("pending")) {
                System.out.println("Call CreateSite success, response: ");
                System.out.println(com.aliyun.teautil.Common.toJSONString(createSiteResponse));
                return createSiteResponse.body;
            }

            currentRetry++;
        }
        throw new TeaException(TeaConverter.buildMap(
            new TeaPair("message", "Asynchronous check failed")
        ));
    }

    public static CreateRecordResponseBody recordSshfp(CreateSiteResponseBody siteSshfpResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateRecord to create resource");
        CreateRecordRequest.CreateRecordRequestData data = new CreateRecordRequest.CreateRecordRequestData()
                .setFingerprint("6262626475636f6d")
                .setType(1)
                .setAlgorithm(1);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest()
                .setRecordName("www.gositecdn.cn")
                .setComment("This is a remark")
                .setSiteId(siteSshfpResponseBody.siteId)
                .setType("SSHFP")
                .setData(data)
                .setTtl(100);
        CreateRecordResponse createRecordResponse = Sample.createRecordWithRetry(client, createRecordRequest);
        System.out.println("Call CreateRecord success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(createRecordResponse));
        return createRecordResponse.body;
    }

    public static CreateRecordResponse createRecordWithRetry(com.aliyun.esa20240910.Client client, CreateRecordRequest createRecordRequest) throws Exception {
        String errorCode = "";
        int retry1 = 0;
        int interval1 = 5000;
        int retry2 = 0;
        int interval2 = 5000;

        while ((retry1 < 10) || (retry2 < 20)) {
            try {
                CreateRecordResponse createRecordResponse = client.createRecord(createRecordRequest);
                System.out.println("Call CreateRecord success, response: ");
                System.out.println(com.aliyun.teautil.Common.toJSONString(createRecordResponse));
                return createRecordResponse;
            } catch (TeaException error) {
                errorCode = error.code;
            }            
            if (errorCode.equals("Site.ServiceBusy")) {
                System.out.println("Call CreateRecord failed, errorCode: Site.ServiceBusy, please retry");
                Thread.sleep(interval1);
                retry1++;
            }

            if (errorCode.equals("TooManyRequests")) {
                System.out.println("Call CreateRecord failed, errorCode: TooManyRequests, please retry");
                Thread.sleep(interval2);
                retry2++;
            }

        }
        throw new TeaException(TeaConverter.buildMap(
            new TeaPair("message", "Call CreateRecord failed")
        ));
    }

    public static void updateRecordSshfp(CreateRecordResponseBody createRecordResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call UpdateRecord to update resource");
        UpdateRecordRequest.UpdateRecordRequestData data = new UpdateRecordRequest.UpdateRecordRequestData()
                .setFingerprint("6464646475636f6d")
                .setType(3)
                .setAlgorithm(3);
        UpdateRecordRequest updateRecordRequest = new UpdateRecordRequest()
                .setComment("test_record_comment")
                .setData(data)
                .setTtl(86400)
                .setRecordId(createRecordResponseBody.recordId);
        UpdateRecordResponse updateRecordResponse = Sample.updateRecordWithRetry(client, updateRecordRequest);
        System.out.println("Call UpdateRecord success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(updateRecordResponse));
    }

    public static UpdateRecordResponse updateRecordWithRetry(com.aliyun.esa20240910.Client client, UpdateRecordRequest updateRecordRequest) throws Exception {
        String errorCode = "";
        int retry1 = 0;
        int interval1 = 5000;
        int retry2 = 0;
        int interval2 = 3000;

        while ((retry1 < 20) || (retry2 < 10)) {
            try {
                UpdateRecordResponse updateRecordResponse = client.updateRecord(updateRecordRequest);
                System.out.println("Call UpdateRecord success, response: ");
                System.out.println(com.aliyun.teautil.Common.toJSONString(updateRecordResponse));
                return updateRecordResponse;
            } catch (TeaException error) {
                errorCode = error.code;
            }            
            if (errorCode.equals("TooManyRequests")) {
                System.out.println("Call UpdateRecord failed, errorCode: TooManyRequests, please retry");
                Thread.sleep(interval1);
                retry1++;
            }

            if (errorCode.equals("Record.ServiceBusy")) {
                System.out.println("Call UpdateRecord failed, errorCode: Record.ServiceBusy, please retry");
                Thread.sleep(interval2);
                retry2++;
            }

        }
        throw new TeaException(TeaConverter.buildMap(
            new TeaPair("message", "Call UpdateRecord failed")
        ));
    }

    public static void destroyRecordSshfp(CreateRecordResponseBody createRecordResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call DeleteRecord to destroy resource");
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest()
                .setRecordId(createRecordResponseBody.recordId);
        DeleteRecordResponse deleteRecordResponse = Sample.deleteRecordWithRetry(client, deleteRecordRequest);
        System.out.println("Call DeleteRecord success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(deleteRecordResponse));
    }

    public static DeleteRecordResponse deleteRecordWithRetry(com.aliyun.esa20240910.Client client, DeleteRecordRequest deleteRecordRequest) throws Exception {
        String errorCode = "";
        int retry1 = 0;
        int interval1 = 5000;
        int retry2 = 0;
        int interval2 = 1000;

        while ((retry1 < 20) || (retry2 < 10)) {
            try {
                DeleteRecordResponse deleteRecordResponse = client.deleteRecord(deleteRecordRequest);
                System.out.println("Call DeleteRecord success, response: ");
                System.out.println(com.aliyun.teautil.Common.toJSONString(deleteRecordResponse));
                return deleteRecordResponse;
            } catch (TeaException error) {
                errorCode = error.code;
            }            
            if (errorCode.equals("TooManyRequests")) {
                System.out.println("Call DeleteRecord failed, errorCode: TooManyRequests, please retry");
                Thread.sleep(interval1);
                retry1++;
            }

            if (errorCode.equals("Record.ServiceBusy")) {
                System.out.println("Call DeleteRecord failed, errorCode: Record.ServiceBusy, please retry");
                Thread.sleep(interval2);
                retry2++;
            }

        }
        throw new TeaException(TeaConverter.buildMap(
            new TeaPair("message", "Call DeleteRecord failed")
        ));
    }

    /**
     * <b>description</b> :
     * <p>Running code may affect the online resources of the current account, please proceed with caution!</p>
     */
    public static void main(String[] args) throws Exception {
        // The code may contain api calls involving fees. Please ensure that you fully understand the charging methods and prices before running.
        // Set the environment variable COST_ACK to true or delete the following judgment to run the sample code.
        String costAcknowledged = System.getenv("COST_ACK");
        if ((null == costAcknowledged) || !costAcknowledged.equals("true")) {
            System.out.println("Running code may affect the online resources of the current account, please proceed with caution!");
            return ;
        }

        // Init client
        com.aliyun.esa20240910.Client esa20240910Client = Sample.createESA20240910Client();
        // Init resource
        PurchaseRatePlanResponseBody ratePlanInstSshfpRespBody = Sample.ratePlanInstSshfp(esa20240910Client);
        CreateSiteResponseBody siteSshfpRespBody = Sample.siteSshfp(ratePlanInstSshfpRespBody, esa20240910Client);
        CreateRecordResponseBody recordSshfpRespBody = Sample.recordSshfp(siteSshfpRespBody, esa20240910Client);
        // update resource
        Sample.updateRecordSshfp(recordSshfpRespBody, esa20240910Client);
        // destroy resource
        Sample.destroyRecordSshfp(recordSshfpRespBody, esa20240910Client);
    }
}
