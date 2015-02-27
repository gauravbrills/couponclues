package com.sapient.couponclues.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.UserTransaction;

public interface UserTransactionRepository extends ElasticsearchRepository<UserTransaction, String> {

}
