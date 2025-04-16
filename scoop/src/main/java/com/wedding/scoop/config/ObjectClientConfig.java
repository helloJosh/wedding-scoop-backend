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
import java.util.List;
import java.util.Objects;

@Configuration
public class ObjectClientConfig {
    @Bean
    public ObjectStorage objectStorage() throws Exception {
// .oci 디렉토리를 resources에서 가져오기
        Path tempDir = Files.createTempDirectory("oci-config");

        // 리스트에 담긴 필요한 파일들
        List<String> filenames = List.of("config", "scoop_wedding.pem");

        for (String filename : filenames) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream(".oci/" + filename)) {
                if (in == null) {
                    throw new FileNotFoundException("Missing file: " + filename);
                }

                Path target = tempDir.resolve(filename);
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        // config 파일을 기반으로 provider 생성
        Path configPath = tempDir.resolve("config");
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configPath.toString(), "DEFAULT");

        return new ObjectStorageClient(provider);
    }
}
