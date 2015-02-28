package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "coupondetails", type = "coupondetails", refreshInterval = "-1")
public class CouponDetails {
    @Id
    private String id;

    private String couponId;

    private String userId;

    private String couponIssuerSite;

    private String discountPc;

    private String issueDate;

    private String validTill;

    private String couponInfo;

    private String prodId;

    private String expireReason;

    private String couponType;

    private String productCategory;

    private String productName;

    private String comments;

    @Transient
    private Integer rank;

}
