package com.sapient.couponclues.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDetails {
    @Id
    private String userId;
    private String couponId;
    private String couponIssuerSite;
    private String discountPc;
    private String issueDate;
    private String validTill;
    private String couponInfo;
    private String prodId;
    private Date updates;
    private String updatedBy;
    private String expireReason;
    private String couponType;

}
