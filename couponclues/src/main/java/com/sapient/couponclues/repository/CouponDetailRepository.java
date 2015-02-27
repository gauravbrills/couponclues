package com.sapient.couponclues.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.CouponDetails;

public interface CouponDetailRepository extends ElasticsearchRepository<CouponDetails, String> {

    List<CouponDetails> findByCouponId(final String couponId);

    List<CouponDetails> findByProdId(final String prodId);

}
