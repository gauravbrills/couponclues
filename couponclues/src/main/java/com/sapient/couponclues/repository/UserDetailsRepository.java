package com.sapient.couponclues.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sapient.couponclues.model.UserDetails;

public interface UserDetailsRepository extends ElasticsearchRepository<UserDetails, String> {

    List<UserDetails> findByPartnerId(final String partnerId);
}