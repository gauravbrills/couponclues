package com.sapient.couponclues.loader;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.couponclues.config.CouponCluesApplication;
import com.sapient.couponclues.predictor.FetchProductsILove;
import com.sapient.couponclues.rest.CouponRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CouponCluesApplication.class)
public class TestBrain {

    @Autowired
    FetchProductsILove fetchProductsILove;

    @Test
    public void testFetchProductsILOve() {

        fetchProductsILove.fetch("user0010");
    }

    @Test
    public void testFetchProductsILOveBycat() {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setCategories(Arrays.asList(new String[] { "kitchen" }));
        fetchProductsILove.fetchByCat("user0010",couponRequest);
    }
}
