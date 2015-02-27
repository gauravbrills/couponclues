package com.sapient.couponclues.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.UserTransaction;

public interface UserTransactionRepository extends ElasticsearchRepository<UserTransaction, String> {

    public List<UserTransaction> findByuserId(final String userId);
}
