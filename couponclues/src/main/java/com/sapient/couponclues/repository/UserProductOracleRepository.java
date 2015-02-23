package com.sapient.couponclues.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.UserProductOracle;

public interface UserProductOracleRepository extends ElasticsearchRepository<UserProductOracle, String> {

    public List<UserProductOracle> findByUserId(final String userId);
}
