package com.sapient.couponclues.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.predictor.ProductAffinityRanker;

@RestController
@RequestMapping(value = "/brain")
public class CreatePredictions {

    @Autowired
    ProductAffinityRanker affinityRanker;

    @RequestMapping("/rankthem")
    Iterable<ProductOracle> rank() {

        return affinityRanker.rank();
    }

}