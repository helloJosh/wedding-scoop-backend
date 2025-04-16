package com.wedding.scoop.config;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Configuration
public class ObjectClientConfig {
    @Bean
    public ObjectStorage objectStorage() throws Exception {

        // 1. resources/.oci 디렉토리 경로 가져오기
        URL ociDirUrl = getClass().getClassLoader().getResource(".oci");
        if (ociDirUrl == null) {
            throw new FileNotFoundException("resources/.oci directory not found");
        }

        // 2. 리소스 디렉토리를 임시 디렉토리로 복사
        Path tempDir = Files.createTempDirectory("oci-config");
        File ociDir = new File(ociDirUrl.toURI());

        for (File file : Objects.requireNonNull(ociDir.listFiles())) {
            Path destPath = tempDir.resolve(file.getName());
            try (InputStream in = new FileInputStream(file)) {
                Files.copy(in, destPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        // 3. 복사한 config 경로로 Provider 생성
        Path configPath = tempDir.resolve("config");
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configPath.toString(), "DEFAULT");

        // 4. ObjectStorageClient 생성
        return new ObjectStorageClient(provider);
    }
}
