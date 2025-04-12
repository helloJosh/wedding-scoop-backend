package com.wedding.scoop.config;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectClientConfig {
    @Bean
    public ObjectStorage objectStorage() throws Exception {
        return new ObjectStorageClient(new ConfigFileAuthenticationDetailsProvider("~/.oci/config", "DEFAULT"));
    }
}
