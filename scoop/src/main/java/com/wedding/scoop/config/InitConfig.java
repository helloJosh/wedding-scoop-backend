//package com.wedding.scoop.config;
//
//import com.wedding.scoop.support.DataUploader;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class InitConfig implements CommandLineRunner {
//    private final DataUploader dataUploader;
//
//    @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto:}")
//    private String ddlAuto;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if ("create".equalsIgnoreCase(ddlAuto)) {
//            log.info("DDL is set to 'create' → Running data upload.");
//            dataUploader.saveVendor();
//
//            dataUploader.saveVendorCategory();
//
//            dataUploader.saveVendorOption();
//
//            dataUploader.saveVendorType();
//
//            dataUploader.upload();
//        } else {
//            log.info("DDL is '{}', skipping data upload.", ddlAuto);
//        }
//    }
//}
