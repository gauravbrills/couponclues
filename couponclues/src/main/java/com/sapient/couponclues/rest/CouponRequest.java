/**
 * 
 */
package com.sapient.couponclues.rest;

import java.util.List;

import lombok.Data;

/**
 * @author grawat
 */
@Data
public class CouponRequest {
    private List<String> categories;
}
