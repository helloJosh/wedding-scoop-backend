package com.wedding.scoop.config;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;

@Configuration
public class ObjectClientConfig {
    @Bean
    public ObjectStorage objectStorage() throws Exception {
        // 클래스패스에 있는 `.oci/config` 리소스를 읽어서 임시 파일로 저장
        InputStream configInputStream = getClass().getClassLoader().getResourceAsStream(".oci/config");

        if (configInputStream == null) {
            throw new FileNotFoundException("OCI config file not found in resources/.oci/config");
        }

        // 임시 파일 생성
        File tempConfigFile = File.createTempFile("oci-config", ".tmp");
        tempConfigFile.deleteOnExit();

        try (OutputStream out = new FileOutputStream(tempConfigFile)) {
            configInputStream.transferTo(out);
        }

        // 생성된 임시 파일 경로로 ConfigFileAuthenticationDetailsProvider 초기화
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(tempConfigFile.getAbsolutePath(), "DEFAULT");

        return new ObjectStorageClient(provider);
    }
}
