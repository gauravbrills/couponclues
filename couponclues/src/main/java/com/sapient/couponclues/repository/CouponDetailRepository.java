package com.sapient.couponclues.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.CouponDetails;

public interface CouponDetailRepository extends ElasticsearchRepository<CouponDetails, String> {

}
