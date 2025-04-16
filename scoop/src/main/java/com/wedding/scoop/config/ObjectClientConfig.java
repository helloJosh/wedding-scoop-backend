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
        // 1. 리소스 안에 있는 `.oci` 폴더를 임시 디렉토리로 복사
        Path tempDir = Files.createTempDirectory("oci-config");

        // 복사할 파일 목록
        String[] files = {"config", "scoop_wedding.pem"};

        for (String file : files) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream(".oci/" + file)) {
                if (in == null) {
                    throw new FileNotFoundException("Missing file: " + file);
                }
                Files.copy(in, tempDir.resolve(file), StandardCopyOption.REPLACE_EXISTING);
            }
        }

        // 2. config 내용 수정: key_file 경로를 절대경로로 바꿔줌
        Path configPath = tempDir.resolve("config");
        Path privateKeyPath = tempDir.resolve("scoop_wedding.pem");

        List<String> lines = Files.readAllLines(configPath);
        List<String> modified = lines.stream()
                .map(line -> line.startsWith("key_file=") ? "key_file=" + privateKeyPath.toAbsolutePath() : line)
                .toList();
        Files.write(configPath, modified);

        // 3. provider 생성
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configPath.toString(), "DEFAULT");

        return new ObjectStorageClient(provider);
    }
}
