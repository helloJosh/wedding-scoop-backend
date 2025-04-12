package com.wedding.scoop.domain.vendor.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.vendor.dto.request.PostVendorRequest;
import com.wedding.scoop.domain.vendor.dto.response.GetAllVendorResponse;
import com.wedding.scoop.domain.vendor.dto.response.GetVendorResponse;
import com.wedding.scoop.domain.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VendorControllerImpl implements VendorController {
    private final VendorService vendorService;

    @Override
    public ApiResponse<Void> saveVendor(PostVendorRequest postVendorRequest, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ApiResponse<List<GetAllVendorResponse>> getVendors(String vendorTypes) {
        return ApiResponse.success(
                vendorService.getAllVendor(vendorTypes),
                "success"
        );
    }

    @Override
    public ApiResponse<GetVendorResponse> getVendor(String vendorTypes, String vendorId) {
        return null;
    }
}
