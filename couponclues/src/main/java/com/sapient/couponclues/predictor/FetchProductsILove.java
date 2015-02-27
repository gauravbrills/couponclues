/**
 * 
 */
package com.sapient.couponclues.predictor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.couponclues.model.CouponDetails;
import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.model.UserTransaction;
import com.sapient.couponclues.repository.CouponDetailRepository;
import com.sapient.couponclues.repository.ProductOracleRepository;
import com.sapient.couponclues.repository.UserTransactionRepository;

/**
 * @author grawat
 */
@Component
public class FetchProductsILove {

    @Autowired
    UserTransactionRepository transactionRepository;

    @Autowired
    ProductOracleRepository productOracleRepository;

    @Autowired
    CouponDetailRepository couponDetailRepository;

    public List<CouponDetails> fetch(final String userId) {

        // fetch products by user transaction history
        List<UserTransaction> userTransactions = transactionRepository.findByuserId(userId);
        List<String> userProducts = new ArrayList<>();
        for (UserTransaction transaction : userTransactions) {
            userProducts.add(transaction.getProductId());
        }
        // get top ranked products
        List<ProductOracle> relevantProducts = new ArrayList<>();
        for (String productId : userProducts) {
            List<ProductOracle> relatedProducts = productOracleRepository.findByproductId1(productId);
            relevantProducts.addAll(relatedProducts);
        }
        // get coupons for relvantProducts
        Collections.sort(relevantProducts, new Comparator<ProductOracle>() {

            @Override
            public int compare(final ProductOracle o1, final ProductOracle o2) {

                return o1.getScore().compareTo(o2.getScore());
            }
        });

        // fetch all coupons for products
        LinkedList<CouponDetails> couponDetails = new LinkedList<>();
        int rank = 1;
        for (ProductOracle productOracle : relevantProducts) {
            if (!userProducts.contains(productOracle.getProductId2())) {
                List<CouponDetails> coupons = couponDetailRepository.findByProdId(productOracle.getProductId2());
                for (CouponDetails details : coupons) {
                    details.setRank(rank);
                }
                rank++;
                couponDetails.addAll(coupons);

            }
        }
        return couponDetails;

    }
}
