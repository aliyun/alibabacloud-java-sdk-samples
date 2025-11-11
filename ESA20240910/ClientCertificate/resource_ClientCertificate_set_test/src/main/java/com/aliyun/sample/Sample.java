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

    public static PurchaseRatePlanResponseBody ratePlanInstCltCert(com.aliyun.esa20240910.Client client) throws Exception {
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

    public static CreateSiteResponseBody siteCltCert(PurchaseRatePlanResponseBody ratePlanInstCltCertResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateSite to create resource");
        CreateSiteRequest createSiteRequest = new CreateSiteRequest()
                .setSiteName("gositecdn.cn")
                .setInstanceId(ratePlanInstCltCertResponseBody.instanceId)
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

    public static CreateClientCertificateResponseBody cltCert(CreateSiteResponseBody siteCltCertResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call CreateClientCertificate to create resource");
        CreateClientCertificateRequest createClientCertificateRequest = new CreateClientCertificateRequest()
                .setSiteId(siteCltCertResponseBody.siteId)
                .setPkeyType("RSA")
                .setValidityDays(365L);
        CreateClientCertificateResponse createClientCertificateResponse = client.createClientCertificate(createClientCertificateRequest);
        System.out.println("Call CreateClientCertificate success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(createClientCertificateResponse));
        return createClientCertificateResponse.body;
    }

    public static void updateCltCert(CreateSiteResponseBody siteCltCertResponseBody, CreateClientCertificateResponseBody createClientCertificateResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call RevokeClientCertificate to update resource");
        RevokeClientCertificateRequest revokeClientCertificateRequest = new RevokeClientCertificateRequest()
                .setSiteId(siteCltCertResponseBody.siteId)
                .setId(createClientCertificateResponseBody.id);
        RevokeClientCertificateResponse revokeClientCertificateResponse = client.revokeClientCertificate(revokeClientCertificateRequest);
        System.out.println("Call RevokeClientCertificate success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(revokeClientCertificateResponse));
    }

    public static void updateCltCert1(CreateSiteResponseBody siteCltCertResponseBody, CreateClientCertificateResponseBody createClientCertificateResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call ActivateClientCertificate to update resource");
        ActivateClientCertificateRequest activateClientCertificateRequest = new ActivateClientCertificateRequest()
                .setSiteId(siteCltCertResponseBody.siteId)
                .setId(createClientCertificateResponseBody.id);
        ActivateClientCertificateResponse activateClientCertificateResponse = client.activateClientCertificate(activateClientCertificateRequest);
        System.out.println("Call ActivateClientCertificate success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(activateClientCertificateResponse));
    }

    public static void updateCltCert2(CreateSiteResponseBody siteCltCertResponseBody, CreateClientCertificateResponseBody createClientCertificateResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call RevokeClientCertificate to update resource");
        RevokeClientCertificateRequest revokeClientCertificateRequest = new RevokeClientCertificateRequest()
                .setSiteId(siteCltCertResponseBody.siteId)
                .setId(createClientCertificateResponseBody.id);
        RevokeClientCertificateResponse revokeClientCertificateResponse = client.revokeClientCertificate(revokeClientCertificateRequest);
        System.out.println("Call RevokeClientCertificate success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(revokeClientCertificateResponse));
    }

    public static void destroyCltCert(CreateSiteResponseBody siteCltCertResponseBody, CreateClientCertificateResponseBody createClientCertificateResponseBody, com.aliyun.esa20240910.Client client) throws Exception {
        System.out.println("Begin Call DeleteClientCertificate to destroy resource");
        DeleteClientCertificateRequest deleteClientCertificateRequest = new DeleteClientCertificateRequest()
                .setSiteId(siteCltCertResponseBody.siteId)
                .setId(createClientCertificateResponseBody.id);
        DeleteClientCertificateResponse deleteClientCertificateResponse = client.deleteClientCertificate(deleteClientCertificateRequest);
        System.out.println("Call DeleteClientCertificate success, response: ");
        System.out.println(com.aliyun.teautil.Common.toJSONString(deleteClientCertificateResponse));
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
        PurchaseRatePlanResponseBody ratePlanInstCltCertRespBody = Sample.ratePlanInstCltCert(esa20240910Client);
        CreateSiteResponseBody siteCltCertRespBody = Sample.siteCltCert(ratePlanInstCltCertRespBody, esa20240910Client);
        CreateClientCertificateResponseBody cltCertRespBody = Sample.cltCert(siteCltCertRespBody, esa20240910Client);
        // update resource
        Sample.updateCltCert(siteCltCertRespBody, cltCertRespBody, esa20240910Client);
        Sample.updateCltCert1(siteCltCertRespBody, cltCertRespBody, esa20240910Client);
        Sample.updateCltCert2(siteCltCertRespBody, cltCertRespBody, esa20240910Client);
        // destroy resource
        Sample.destroyCltCert(siteCltCertRespBody, cltCertRespBody, esa20240910Client);
    }
}
