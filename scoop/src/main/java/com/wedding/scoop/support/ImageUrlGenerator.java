package com.wedding.scoop.support;

import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.responses.CreatePreauthenticatedRequestResponse;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageUrlGenerator {
    private final ObjectStorage client;

    /**
     * 	•	Object name: 더채플논현/라포레/1579080443_img_5309_3_1736304672.jpg
     * 	•	Namespace: axfovfp5n7kj
     * 	•	Bucket: wedding-scoop
     * 	•	Region: ap-chuncheon-1
     */
    public String generateSignedUrl(String objectName) {
        String namespace = "axfovfp5n7kj";
        String bucketName = "wedding-scoop";
        String region = "ap-chuncheon-1";

        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneOffset.UTC).plusDays(1);
        Date expiresAt = Date.from(offsetDateTime.toInstant());

        CreatePreauthenticatedRequestDetails details = CreatePreauthenticatedRequestDetails.builder()
                .name("temp-access-" + System.currentTimeMillis())
                .objectName(objectName)
                .accessType(CreatePreauthenticatedRequestDetails.AccessType.ObjectRead)
                .timeExpires(expiresAt) // 유효기간: 1일
                .build();

        CreatePreauthenticatedRequestRequest parRequest = CreatePreauthenticatedRequestRequest.builder()
                .namespaceName(namespace)
                .bucketName(bucketName)
                .createPreauthenticatedRequestDetails(details)
                .build();

        CreatePreauthenticatedRequestResponse response = client.createPreauthenticatedRequest(parRequest);
        PreauthenticatedRequest par = response.getPreauthenticatedRequest();

        String encodedPath = URLEncoder.encode(par.getAccessUri(), StandardCharsets.UTF_8);
        String fullUrl = "https://objectstorage." + region + ".oraclecloud.com" + par.getAccessUri();

        log.info("📦 서명된 접근 URL: {}", fullUrl);
        return fullUrl;
    }
}
