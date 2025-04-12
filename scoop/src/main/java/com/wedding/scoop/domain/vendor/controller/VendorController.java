package com.wedding.scoop.domain.vendor.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.vendor.dto.request.PostVendorRequest;
import com.wedding.scoop.domain.vendor.dto.response.GetAllVendorResponse;
import com.wedding.scoop.domain.vendor.dto.response.GetVendorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/api/vendors")
@Tag(name = "Vendor", description = "업체 API")
public interface VendorController {
    @Operation(
            summary = "업체 저장 API",
            description = "업체 저장 API"
    )
    @PostMapping("")
    ApiResponse<Void> saveVendor(@Valid @RequestBody PostVendorRequest postVendorRequest,
                            BindingResult bindingResult);

    /// GET /v1/api/vendors/HALL
    @Operation(
            summary = "업체 전체 조회 API",
            description = "업체 전체 조회"
    )
    @GetMapping("/{vendorType}")
    ApiResponse<List<GetAllVendorResponse>> getVendors(
            @PathVariable("vendorType") String vendorTypes
    );

    @Operation(
            summary = "업체 단일 조회 API",
            description = "업체 단일 조회"
    )
    @GetMapping("/{vendorType}/{vendorId}")
    ApiResponse<GetVendorResponse> getVendor(
            @PathVariable("vendorType") String vendorTypes,
            @PathVariable("vendorId") String vendorId);
}