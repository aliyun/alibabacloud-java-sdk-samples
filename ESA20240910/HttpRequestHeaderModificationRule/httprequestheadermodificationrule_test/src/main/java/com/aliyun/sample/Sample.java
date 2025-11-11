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

    public static CreateHttpRequestHeaderModificationRuleResponseBody reqHdrModRule(CreateSiteResponseBody siteResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateHttpRequestHeaderModificationRule to create resource");
        CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification requestHeaderModification = new CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification()
                .setType("static")
                .setValue("add")
                .setOperation("add")
                .setName("testadd");
        CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification requestHeaderModification1 = new CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification()
                .setOperation("del")
                .setName("testdel");
        CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification requestHeaderModification2 = new CreateHttpRequestHeaderModificationRuleRequest.CreateHttpRequestHeaderModificationRuleRequestRequestHeaderModification()
                .setType("dynamic")
                .setValue("ip.geoip.country")
                .setOperation("modify")
                .setName("testmodify");
        CreateHttpRequestHeaderModificationRuleRequest createHttpRequestHeaderModificationRuleRequest = new CreateHttpRequestHeaderModificationRuleRequest()
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("on")
                .setRule("(http.host eq \"video.example.com\")")
                .setSequence(1)
                .setSiteVersion(0)
                .setRuleName("test")
                .setRequestHeaderModification(java.util.Arrays.asList(
                    requestHeaderModification,
                    requestHeaderModification1,
                    requestHeaderModification2
                ));
        CreateHttpRequestHeaderModificationRuleResponse createHttpRequestHeaderModificationRuleResponse = client.createHttpRequestHeaderModificationRule(createHttpRequestHeaderModificationRuleRequest);
        System.out.println("Call CreateHttpRequestHeaderModificationRule success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(createHttpRequestHeaderModificationRuleResponse));
        return createHttpRequestHeaderModificationRuleResponse.body;
    }

    public static void updateReqHdrModRule(CreateSiteResponseBody siteResponseBody, CreateHttpRequestHeaderModificationRuleResponseBody createHttpRequestHeaderModificationRuleResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call UpdateHttpRequestHeaderModificationRule to update resource");
        UpdateHttpRequestHeaderModificationRuleRequest.UpdateHttpRequestHeaderModificationRuleRequestRequestHeaderModification requestHeaderModification = new UpdateHttpRequestHeaderModificationRuleRequest.UpdateHttpRequestHeaderModificationRuleRequestRequestHeaderModification()
                .setType("static")
                .setValue("modify1")
                .setOperation("modify")
                .setName("testmodify1");
        UpdateHttpRequestHeaderModificationRuleRequest updateHttpRequestHeaderModificationRuleRequest = new UpdateHttpRequestHeaderModificationRuleRequest()
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("off")
                .setRule("(http.request.uri eq \"/content?page=1234\")")
                .setSequence(1)
                .setRuleName("test_modify")
                .setRequestHeaderModification(java.util.Arrays.asList(
                    requestHeaderModification
                ))
                .setConfigId(createHttpRequestHeaderModificationRuleResponseBody.configId);
        UpdateHttpRequestHeaderModificationRuleResponse updateHttpRequestHeaderModificationRuleResponse = client.updateHttpRequestHeaderModificationRule(updateHttpRequestHeaderModificationRuleRequest);
        System.out.println("Call UpdateHttpRequestHeaderModificationRule success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(updateHttpRequestHeaderModificationRuleResponse));
    }

    public static void destroyReqHdrModRule(CreateSiteResponseBody siteResponseBody, CreateHttpRequestHeaderModificationRuleResponseBody createHttpRequestHeaderModificationRuleResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call DeleteHttpRequestHeaderModificationRule to destroy resource");
        DeleteHttpRequestHeaderModificationRuleRequest deleteHttpRequestHeaderModificationRuleRequest = new DeleteHttpRequestHeaderModificationRuleRequest()
                .setSiteId(siteResponseBody.siteId)
                .setConfigId(createHttpRequestHeaderModificationRuleResponseBody.configId);
        DeleteHttpRequestHeaderModificationRuleResponse deleteHttpRequestHeaderModificationRuleResponse = client.deleteHttpRequestHeaderModificationRule(deleteHttpRequestHeaderModificationRuleRequest);
        System.out.println("Call DeleteHttpRequestHeaderModificationRule success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(deleteHttpRequestHeaderModificationRuleResponse));
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
        PurchaseRatePlanResponseBody ratePlanInstRespBody = Sample.ratePlanInst(esa20240910Client);
        CreateSiteResponseBody siteRespBody = Sample.site(ratePlanInstRespBody, esa20240910Client);
        CreateHttpRequestHeaderModificationRuleResponseBody reqHdrModRuleRespBody = Sample.reqHdrModRule(siteRespBody, esa20240910Client);
        // update resource
        Sample.updateReqHdrModRule(siteRespBody, reqHdrModRuleRespBody, esa20240910Client);
        // destroy resource
        Sample.destroyReqHdrModRule(siteRespBody, reqHdrModRuleRespBody, esa20240910Client);
    }
}
