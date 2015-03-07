package com.sapient.couponclues.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/fetchCouponsILove/{userId}", method = RequestMethod.GET)
    List<CouponDetails> fetchCouponsILove(@PathVariable("userId")
    final String userId) {

        System.out.println(userId);
        return fetchProductsILove.fetch(userId);

    }

    @RequestMapping(value = "/fetchCouponsILove/{userId}", method = RequestMethod.POST)
    List<CouponDetails> fetchCouponsILoveByCategory(@PathVariable("userId")
    final String userId, @RequestBody
    final CouponRequest couponRequest) {

        return fetchProductsILove.fetchByCat(userId, couponRequest);

    }

    @RequestMapping("/rankthem")
    Iterable<ProductOracle> rank() {

        return affinityRanker.rank();
    }

}