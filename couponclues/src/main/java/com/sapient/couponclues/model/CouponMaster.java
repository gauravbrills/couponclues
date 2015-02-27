package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponMaster {
    @Id
    private String coupondCategoryId;
    private String couponType;
    private String couponRank;
    private String couponDesc;
    // ?? issue date , expire date ... no that will be in coupon master

}
