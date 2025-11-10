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

    public static PurchaseRatePlanResponseBody ratePlanInst(com.aliyun.esa20240910.Client client) throws Exception {
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

    public static CreateSiteResponseBody site(PurchaseRatePlanResponseBody ratePlanInstResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateSite to create resource");
        CreateSiteRequest createSiteRequest = new CreateSiteRequest()
                .setSiteName("gositecdn.cn")
                .setInstanceId(ratePlanInstResponseBody.instanceId)
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

    public static CreateVideoProcessingResponseBody videoProc(CreateSiteResponseBody siteResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateVideoProcessing to create resource");
        CreateVideoProcessingRequest createVideoProcessingRequest = new CreateVideoProcessingRequest()
                .setVideoSeekEnable("on")
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("on")
                .setFlvVideoSeekMode("by_byte")
                .setMp4SeekEnd("end")
                .setFlvSeekStart("start")
                .setRule("(http.host eq \"video.example.com\")")
                .setSequence(1)
                .setMp4SeekStart("start")
                .setSiteVersion(0)
                .setFlvSeekEnd("end")
                .setRuleName("test");
        CreateVideoProcessingResponse createVideoProcessingResponse = client.createVideoProcessing(createVideoProcessingRequest);
        System.out.println("Call CreateVideoProcessing success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(createVideoProcessingResponse));
        return createVideoProcessingResponse.body;
    }

    public static void updateVideoProc(CreateSiteResponseBody siteResponseBody, CreateVideoProcessingResponseBody createVideoProcessingResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call UpdateVideoProcessing to update resource");
        UpdateVideoProcessingRequest updateVideoProcessingRequest = new UpdateVideoProcessingRequest()
                .setVideoSeekEnable("off")
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("off")
                .setFlvVideoSeekMode("by_time")
                .setMp4SeekEnd("e")
                .setFlvSeekStart("s")
                .setRule("(http.request.uri eq \"/content?page=1234\")")
                .setSequence(1)
                .setMp4SeekStart("s")
                .setFlvSeekEnd("e")
                .setRuleName("test_modify")
                .setConfigId(createVideoProcessingResponseBody.configId);
        UpdateVideoProcessingResponse updateVideoProcessingResponse = client.updateVideoProcessing(updateVideoProcessingRequest);
        System.out.println("Call UpdateVideoProcessing success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(updateVideoProcessingResponse));
    }

    public static void destroyVideoProc(CreateSiteResponseBody siteResponseBody, CreateVideoProcessingResponseBody createVideoProcessingResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call DeleteVideoProcessing to destroy resource");
        DeleteVideoProcessingRequest deleteVideoProcessingRequest = new DeleteVideoProcessingRequest()
                .setSiteId(siteResponseBody.siteId)
                .setConfigId(createVideoProcessingResponseBody.configId);
        DeleteVideoProcessingResponse deleteVideoProcessingResponse = client.deleteVideoProcessing(deleteVideoProcessingRequest);
        System.out.println("Call DeleteVideoProcessing success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(deleteVideoProcessingResponse));
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
        //resource_VideoProcessing_test
        PurchaseRatePlanResponseBody ratePlanInstRespBody = Sample.ratePlanInst(esa20240910Client);
        //resource_Site_VideoProcessing_test
        CreateSiteResponseBody siteRespBody = Sample.site(ratePlanInstRespBody, esa20240910Client);
        CreateVideoProcessingResponseBody videoProcRespBody = Sample.videoProc(siteRespBody, esa20240910Client);
        // update resource
        Sample.updateVideoProc(siteRespBody, videoProcRespBody, esa20240910Client);
        // destroy resource
        Sample.destroyVideoProc(siteRespBody, videoProcRespBody, esa20240910Client);
    }
}
