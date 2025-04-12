package com.wedding.scoop.domain.vendor.dto.response;

import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllVendorResponse {
    private VendorTypes vendorTypes;
    private String vendorName;
    private String type;
    private String option;
    private Double price;
    private String thumbnailUrl;
}
