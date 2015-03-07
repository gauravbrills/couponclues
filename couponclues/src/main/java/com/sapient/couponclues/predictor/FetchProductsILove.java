/*
 * FetchProductsILove.java Copyright 2014, Wellington Management Company, LLP. 75 State Street, Boston, MA 02109, U.S.A. All Rights
 * Reserved. This software is the confidential and proprietary information of Wellington Management Company LLP. You shall not
 * disclose such confidential information and shall use it only in accordance with the terms of the license agreement you entered
 * into with Wellington Management.
 * @author Gaurav Rawat
 */
package com.sapient.couponclues.predictor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.couponclues.model.CouponDetails;
import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.model.UserDetails;
import com.sapient.couponclues.model.UserTransaction;
import com.sapient.couponclues.repository.CouponDetailRepository;
import com.sapient.couponclues.repository.ProductOracleRepository;
import com.sapient.couponclues.repository.UserDetailsRepository;
import com.sapient.couponclues.repository.UserTransactionRepository;
import com.sapient.couponclues.rest.CouponRequest;

/**
 * The Class FetchProductsILove.
 */
@Component
public class FetchProductsILove {

    /** The Constant MAXSPECIALITEMS. */
    private static final int MAXSPECIALITEMS = 5;

    /** The transaction repository. */
    @Autowired
    UserTransactionRepository transactionRepository;

    /** The product oracle repository. */
    @Autowired
    ProductOracleRepository productOracleRepository;

    /** The details repository. */
    @Autowired
    UserDetailsRepository detailsRepository;

    /** The coupon detail repository. */
    @Autowired
    CouponDetailRepository couponDetailRepository;

    /**
     * Fetch.
     * 
     * @param userId the user id
     * @return the list
     */
    public List<CouponDetails> fetch(final String userId) {

        List<CouponDetails> coupons = fetchByUserID(userId, false);
        // get me
        UserDetails me = detailsRepository.findOne(userId);
        detailsRepository.findAll();
        // get relationships
        if (me != null && me.getPartnerId() != null)
            getReln(userId, coupons, me);
        return coupons;
    }

    /**
     * Fetch by cat.
     * 
     * @param userId the user id
     * @param couponRequest the coupon request
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<CouponDetails> fetchByCat(final String userId, final CouponRequest couponRequest) {

        final List<CouponDetails> coupons = fetch(userId);
        if (couponRequest == null)
            return null;
        return (List<CouponDetails>) CollectionUtils.select(coupons, new Predicate() {

            @Override
            public boolean evaluate(final Object on) {

                CouponDetails a = (CouponDetails) on;
                for (String category : couponRequest.getCategories()) {
                    if (a.getProductCategory().equals(category))
                        return true;
                }
                return false;
            }
        });

    }

    /**
     * Fetch by user id.
     * 
     * @param userId the user id
     * @param special the special
     * @return the list
     */
    public List<CouponDetails> fetchByUserID(final String userId, final boolean special) {

        // fetch products by user transaction history
        List<String> userProducts = getProductsForUser(userId);
        // get top ranked products
        List<ProductOracle> relevantProductsForUser = new ArrayList<>();
        getTopRankedProducts(userProducts, relevantProductsForUser);

        // fetch all coupons for products
        LinkedList<CouponDetails> couponDetails = new LinkedList<>();
        int rank = 1;
        for (ProductOracle productOracle : relevantProductsForUser) {
            if (!userProducts.contains(productOracle.getProductId2())) {
                List<CouponDetails> coupons = couponDetailRepository.findByProdId(productOracle.getProductId2());
                for (CouponDetails details : coupons) {
                    if (!special)
                        details.setRank(rank);
                }
                rank++;
                couponDetails.addAll(coupons);

            }
        }
        return couponDetails;

    }

    /**
     * Gets the products for user.
     * 
     * @param userId the user id
     * @return the products for user
     */
    private List<String> getProductsForUser(final String userId) {

        List<UserTransaction> userTransactions = transactionRepository.findByuserId(userId);
        List<String> userProducts = new ArrayList<>();
        for (UserTransaction transaction : userTransactions) {
            userProducts.add(transaction.getProductId());
        }
        return userProducts;
    }

    /**
     * Gets the reln.
     * 
     * @param userId the user id
     * @param coupons the coupons
     * @param me the me
     * @return the reln
     */
    private void getReln(final String userId, final List<CouponDetails> coupons, final UserDetails me) {

        UserDetails partner = detailsRepository.findOne(me.getPartnerId());
        if (null != partner) {
            List<CouponDetails> relnCoupons = fetchByUserID(partner.getUserId(), true);
            if (relnCoupons != null)
                for (int i = 0; i < (coupons.size() > MAXSPECIALITEMS ? MAXSPECIALITEMS : coupons.size()); i++) {
                    coupons.add(relnCoupons.get(i));
                }
        }
    }

    /**
     * Gets the top ranked products.
     * 
     * @param userProducts the user products
     * @param relevantProducts the relevant products
     * @return the top ranked products
     */
    private void getTopRankedProducts(final List<String> userProducts, final List<ProductOracle> relevantProducts) {

        for (String productId : userProducts) {
            List<ProductOracle> relatedProducts = productOracleRepository.findByproductId1(productId);
            relevantProducts.addAll(relatedProducts);
        }
        // get coupons for relvantProducts
        Collections.sort(relevantProducts, new Comparator<ProductOracle>() {

            @Override
            public int compare(final ProductOracle o1, final ProductOracle o2) {

                return o2.getScore().compareTo(o1.getScore());
            }
        });
    }

}
