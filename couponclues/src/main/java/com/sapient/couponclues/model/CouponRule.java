/**
 * 
 */
package com.sapient.couponclues.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author grawat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "couponRule", type = "couponRule", refreshInterval = "-1")
public class CouponRule {
    @Id
    private String id;

    private String el;

    private String name;

    private String description;
}
