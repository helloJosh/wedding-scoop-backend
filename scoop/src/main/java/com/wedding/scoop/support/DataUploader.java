//package com.wedding.scoop.support;
//
//import com.opencsv.CSVReader;
//import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
//import com.oracle.bmc.objectstorage.ObjectStorage;
//import com.oracle.bmc.objectstorage.ObjectStorageClient;
//import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
//import com.oracle.bmc.objectstorage.responses.PutObjectResponse;
//import com.wedding.scoop.domain.vendor.entity.*;
//import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
//import com.wedding.scoop.domain.vendor.repository.*;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@Transactional
//public class DataUploader {
//    private final String ROOT_PATH = "/Users/administrator/Documents/projects/wedding";
//
//    private final VendorRepository vendorRepository;
//    private final TypeRepository typeRepository;
//    private final VendorTypeRepository vendorTypeRepository;
//    private final CategoryRepository categoryRepository;
//    private final OptionRepository optionRepository;
//    private final VendorCategoryRepository vendorCategoryRepository;
//    private final VendorOptionRepository vendorOptionRepository;
//    private final VendorImageRepository vendorImageRepository;
//    private final ObjectStorage client;
//
//
//    public void upload() {
//        try {
//            uploadImages(ROOT_PATH + "/img", "wedding-scoop", "axfovfp5n7kj");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    public void uploadImages(String rootPath, String bucketName, String namespace) throws Exception {
//        File rootDir = new File(rootPath);
//        File[] vendorDirs = rootDir.listFiles(File::isDirectory);
//
//        for (File vendorDir : vendorDirs) {
//            String vendor = vendorDir.getName();
//            for (File typeDir : vendorDir.listFiles(File::isDirectory)) {
//                String type = typeDir.getName();
//                for (File imageFile : typeDir.listFiles((d, name) -> name.endsWith(".jpg"))) {
//
//                    String objectName = vendor + "/" + type + "/" + imageFile.getName();
//                    log.info("Uploading: {}", objectName);
//
//                    PutObjectRequest request = PutObjectRequest.builder()
//                            .namespaceName(namespace)
//                            .bucketName(bucketName)
//                            .objectName(objectName)
//                            .putObjectBody(new FileInputStream(imageFile))
//                            .contentLength(Files.size(imageFile.toPath()))
//                            .build();
//
//                    PutObjectResponse response = client.putObject(request);
//
//                    Vendor vendord = vendorRepository.findByName(vendor)
//                            .orElseThrow(() -> new RuntimeException("Vendor not found: " + vendor));
//
//                    Image image = new Image(objectName);
//
//                    VendorImage vendorImage = new VendorImage(vendord, image, type);
//
//                    vendorImageRepository.saveAndFlush(vendorImage);
//                    log.info("Uploaded {} → ETag={}", objectName, response.getETag());
//                }
//            }
//        }
//    }
//
//    public void saveVendor() {
//        try (CSVReader reader = new CSVReader(new FileReader(ROOT_PATH + "/csv/vendor.csv", StandardCharsets.UTF_8))) {
//
//            String[] nextLine;
//            reader.readNext(); // header skip
//
//            while ((nextLine = reader.readNext()) != null) {
//                Vendor vendor = new Vendor();
//                vendor.setName(nextLine[0]);
//                vendor.setUrl(nextLine[1]);
//                vendor.setPhoneNumber(nextLine[2]);
//                vendor.setKakaoUrl(nextLine[3]);
//                vendor.setIgUrl(nextLine[4]);
//                vendor.setAddress(nextLine[5]);
//                vendor.setVendorTypes(VendorTypes.valueOf(nextLine[6]));
//
//                vendorRepository.saveAndFlush(vendor);
//
//                log.info("Vendor Inserted: {}", vendor.getName());
//            }
//
//        } catch (Exception e) {
//            log.error("CSV Insert Error", e);
//        }
//    }
//
//    public void saveVendorType() {
//        try (CSVReader reader = new CSVReader(new FileReader(ROOT_PATH + "/csv/vendor-type.csv", StandardCharsets.UTF_8))) {
//            String[] line;
//            reader.readNext(); // header skip
//
//            while ((line = reader.readNext()) != null) {
//                String vendorName = line[0].trim();
//                String typeName = line[1].trim();
//                String detailName = line[2].trim();
//                Double price = Double.parseDouble(line[3].trim());
//                String vendorTypeSring = line[4].trim();
//
//                Vendor vendor = vendorRepository.findByName(vendorName)
//                        .orElseThrow(() -> new RuntimeException("Vendor not found: " + vendorName));
//
//                Type type = typeRepository.findByName(typeName)
//                        .orElseGet(() -> {
//                            Type newType = new Type();
//                            newType.setName(typeName);
//                            newType.setVendorTypes(VendorTypes.valueOf(vendorTypeSring)); // 필요시 설정
//                            return typeRepository.saveAndFlush(newType);
//                        });
//
//                VendorType vendorType = new VendorType();
//                vendorType.setDetailName(detailName);
//                vendorType.setPrice(price);
//                vendorType.setActive(true);
//                vendorType.setType(type);
//                vendorType.setVendor(vendor);
//                vendorTypeRepository.saveAndFlush(vendorType);
//
//                log.info("✅ Inserted VendorType => vendor: {}, type: {}, detailName: {}, price: {}", vendorName, typeName, detailName, price);
//            }
//
//        } catch (Exception e) {
//            log.error("CSV Insert Error", e);
//        }
//    }
//
//    public void saveVendorCategory() {
//        try (CSVReader reader = new CSVReader(new FileReader(ROOT_PATH + "/csv/vendor-category.csv", StandardCharsets.UTF_8))) {
//            String[] line;
//            reader.readNext(); // header skip
//
//            while ((line = reader.readNext()) != null) {
//                String vendorName = line[0].trim();
//                String categoryName = line[1].trim();
//                String detailName = line[2].trim();
//                String vendorTypeSring = line[3].trim();
//
//                Vendor vendor = vendorRepository.findByName(vendorName)
//                        .orElseThrow(() -> new RuntimeException("Vendor not found: " + vendorName));
//
//                Category category = categoryRepository.findByName(categoryName)
//                        .orElseGet(() -> {
//                            Category newCategory = new Category();
//                            newCategory.setName(categoryName);
//                            newCategory.setVendorTypes(VendorTypes.valueOf(vendorTypeSring)); // 필요시 설정
//                            return categoryRepository.saveAndFlush(newCategory);
//                        });
//
//                VendorCategory vendorCategory = new VendorCategory();
//                vendorCategory.setDetailName(detailName);
//                vendorCategory.setActive(true);
//                vendorCategory.setVendor(vendor);
//                vendorCategory.setCategory(category);
//
//                vendorCategoryRepository.saveAndFlush(vendorCategory);
//                log.info("✅ Inserted VendorCategory => vendor: {}, categoryName: {}, detail Name: {}, type: {}", vendorName, categoryName, detailName, vendorTypeSring);
//            }
//
//        } catch (Exception e) {
//            log.error("CSV Insert Error", e);
//        }
//    }
//
//    public void saveVendorOption() {
//        try (CSVReader reader = new CSVReader(new FileReader(ROOT_PATH + "/csv/vendor-option.csv", StandardCharsets.UTF_8))) {
//            String[] line;
//            reader.readNext(); // header skip
//
//            while ((line = reader.readNext()) != null) {
//                String vendorName = line[0].trim();
//                String optionName = line[1].trim();
//                String detailName = line[2].trim();
//                Integer price = Integer.parseInt(line[3].trim());
//                Integer pricePerCount = Integer.parseInt(line[4].trim());
//                Integer defaultCount = Integer.parseInt(line[5].trim());
//                String vendorTypeSring = line[6].trim();
//
//                Vendor vendor = vendorRepository.findByName(vendorName)
//                        .orElseThrow(() -> new RuntimeException("Vendor not found: " + vendorName));
//
//                Option option = optionRepository.findByName(optionName)
//                        .orElseGet(() -> {
//                            Option newOption = new Option();
//                            newOption.setName(optionName);
//                            newOption.setVendorTypes(VendorTypes.valueOf(vendorTypeSring)); // 필요시 설정
//                            return optionRepository.saveAndFlush(newOption);
//                        });
//
//                VendorOption vendorOption = new VendorOption();
//                vendorOption.setPrice(price);
//                vendorOption.setPricePerCount(pricePerCount);
//                vendorOption.setDefaultCount(defaultCount);
//                vendorOption.setDetailName(detailName);
//                vendorOption.setActive(true);
//                vendorOption.setVendor(vendor);
//                vendorOption.setOption(option);
//
//                vendorOptionRepository.saveAndFlush(vendorOption);
//
//            }
//
//        } catch (Exception e) {
//            log.error("CSV Insert Error", e);
//        }
//    }
//}
