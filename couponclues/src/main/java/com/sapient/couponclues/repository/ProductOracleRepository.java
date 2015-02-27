package com.sapient.couponclues.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.ProductOracle;

public interface ProductOracleRepository extends ElasticsearchRepository<ProductOracle, String> {

    List<ProductOracle> findByproductId1(final String productId);

}
