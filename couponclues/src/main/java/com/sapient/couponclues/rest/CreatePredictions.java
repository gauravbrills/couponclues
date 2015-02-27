package com.sapient.couponclues.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.couponclues.model.CouponDetails;
import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.predictor.FetchProductsILove;
import com.sapient.couponclues.predictor.ProductAffinityRanker;

@RestController
@RequestMapping(value = "/brain")
public class CreatePredictions {

    @Autowired
    ProductAffinityRanker affinityRanker;

    @Autowired
    FetchProductsILove fetchProductsILove;

    @RequestMapping("/fetchCouponsILove/{userId}")
    List<CouponDetails> fetchCouponsILove(@PathParam("userId")
    final String userId) {

        return fetchProductsILove.fetch(userId);

    }

    @RequestMapping("/rankthem")
    Iterable<ProductOracle> rank() {

        return affinityRanker.rank();
    }

}