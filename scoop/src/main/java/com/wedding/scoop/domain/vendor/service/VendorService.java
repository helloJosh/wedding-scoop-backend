package com.wedding.scoop.domain.vendor.service;

import com.wedding.scoop.domain.vendor.dto.response.GetAllVendorResponse;
import com.wedding.scoop.domain.vendor.entity.Vendor;
import com.wedding.scoop.domain.vendor.entity.VendorImage;
import com.wedding.scoop.domain.vendor.entity.VendorType;
import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
import com.wedding.scoop.domain.vendor.repository.VendorImageRepository;
import com.wedding.scoop.domain.vendor.repository.VendorRepository;
import com.wedding.scoop.domain.vendor.repository.VendorTypeRepository;
import com.wedding.scoop.exception.notfound.ImageNotFoundException;
import com.wedding.scoop.support.ImageUrlGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class VendorService {
    private final VendorRepository vendorRepository;
    private final VendorTypeRepository vendorTypeRepository;
    private final VendorImageRepository vendorImageRepository;
    private final ImageUrlGenerator imageUrlGenerator;

    public List<GetAllVendorResponse> getAllVendor(String vendorType) {
        List<GetAllVendorResponse> responses = new ArrayList<>();

        List<Vendor> vendors =vendorRepository.findAllByVendorTypes(
                VendorTypes.valueOf(
                        vendorType.toUpperCase()
                )
        );

        for (Vendor vendor : vendors) {
            List<VendorType> vendorTypes = vendorTypeRepository.findAllByVendor(vendor);
            VendorImage vendorImage = vendorImageRepository.findByVendorAndType(vendor, "thumbnail")
                    .orElseThrow(
                            ()-> new ImageNotFoundException(vendor.getName() + "image not found")
                    );
            String objectName = vendorImage.getImage().getObjectName();

            // 가장 싼 타입 선택
            VendorType cheapestType = vendorTypes.stream()
                    .min(Comparator.comparingDouble(VendorType::getPrice))
                    .orElse(null);

            if (cheapestType == null) continue;

            responses.add(
                    GetAllVendorResponse.builder()
                    .thumbnailUrl(imageUrlGenerator.generateSignedUrl(objectName))
                    .vendorTypes(vendor.getVendorTypes())
                    .vendorName(vendor.getName())
                    .price(cheapestType.getPrice())
                    .type(cheapestType.getDetailName())
                    .option(cheapestType.getType().getName())
                    .build()
            );
        }
        return responses;
    }
}
