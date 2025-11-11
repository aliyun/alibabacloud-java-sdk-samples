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

    public static CreateHttpsBasicConfigurationResponseBody httpsCfg(CreateSiteResponseBody siteResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateHttpsBasicConfiguration to create resource");
        CreateHttpsBasicConfigurationRequest createHttpsBasicConfigurationRequest = new CreateHttpsBasicConfigurationRequest()
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("on")
                .setHttps("on")
                .setRule("true")
                .setRuleName("test2");
        CreateHttpsBasicConfigurationResponse createHttpsBasicConfigurationResponse = client.createHttpsBasicConfiguration(createHttpsBasicConfigurationRequest);
        System.out.println("Call CreateHttpsBasicConfiguration success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(createHttpsBasicConfigurationResponse));
        return createHttpsBasicConfigurationResponse.body;
    }

    public static void updateHttpsCfg(CreateSiteResponseBody siteResponseBody, CreateHttpsBasicConfigurationResponseBody createHttpsBasicConfigurationResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call UpdateHttpsBasicConfiguration to update resource");
        UpdateHttpsBasicConfigurationRequest updateHttpsBasicConfigurationRequest = new UpdateHttpsBasicConfigurationRequest()
                .setSiteId(siteResponseBody.siteId)
                .setRuleEnable("on")
                .setHttps("off")
                .setRule("true")
                .setRuleName("test2")
                .setConfigId(createHttpsBasicConfigurationResponseBody.configId);
        UpdateHttpsBasicConfigurationResponse updateHttpsBasicConfigurationResponse = client.updateHttpsBasicConfiguration(updateHttpsBasicConfigurationRequest);
        System.out.println("Call UpdateHttpsBasicConfiguration success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(updateHttpsBasicConfigurationResponse));
    }

    public static void destroyHttpsCfg(CreateSiteResponseBody siteResponseBody, CreateHttpsBasicConfigurationResponseBody createHttpsBasicConfigurationResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call DeleteHttpsBasicConfiguration to destroy resource");
        DeleteHttpsBasicConfigurationRequest deleteHttpsBasicConfigurationRequest = new DeleteHttpsBasicConfigurationRequest()
                .setSiteId(siteResponseBody.siteId)
                .setConfigId(createHttpsBasicConfigurationResponseBody.configId);
        DeleteHttpsBasicConfigurationResponse deleteHttpsBasicConfigurationResponse = client.deleteHttpsBasicConfiguration(deleteHttpsBasicConfigurationRequest);
        System.out.println("Call DeleteHttpsBasicConfiguration success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(deleteHttpsBasicConfigurationResponse));
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
        CreateHttpsBasicConfigurationResponseBody httpsCfgRespBody = Sample.httpsCfg(siteRespBody, esa20240910Client);
        // update resource
        Sample.updateHttpsCfg(siteRespBody, httpsCfgRespBody, esa20240910Client);
        // destroy resource
        Sample.destroyHttpsCfg(siteRespBody, httpsCfgRespBody, esa20240910Client);
    }
}
