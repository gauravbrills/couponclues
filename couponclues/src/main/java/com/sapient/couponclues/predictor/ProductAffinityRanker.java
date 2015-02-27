/*
 * ProductAffinityRanker.java Copyright 2014, Wellington Management Company, LLP. 75 State Street, Boston, MA 02109, U.S.A. All
 * Rights Reserved. This software is the confidential and proprietary information of Wellington Management Company LLP. You shall
 * not disclose such confidential information and shall use it only in accordance with the terms of the license agreement you
 * entered into with Wellington Management.
 * @author Gaurav Rawat
 */
package com.sapient.couponclues.predictor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.model.UserTransaction;
import com.sapient.couponclues.repository.ProductOracleRepository;
import com.sapient.couponclues.repository.UserTransactionRepository;

/**
 * The Class ProductAffinityRanker.
 */
@Component
/** The Constant log. */
@Log4j
public class ProductAffinityRanker {

    /** The user transaction repository. */
    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    ProductOracleRepository productOracleRepository;

    /** The transactions. */
    private List<UserTransaction> transactions;

    /**
     * Calculate jaccard index.
     * 
     * @param commonoccurence the commonoccurence
     * @param totalOccurence the total occurence
     * @return the double
     */
    private Double calculateJaccardIndex(final Double commonoccurence, final Double totalOccurence) {

        return commonoccurence / totalOccurence;
    }

    /**
     * Creates the product2 product index.
     * 
     * @param productUserMatrix the product user matrix
     * @param product2productMatrix the product2product matrix
     * @param setSize
     * @param productOracles the product oracles
     */
    private void createProduct2ProductIndex(final MatrixOfObjects productUserMatrix,
                                            final MatrixOfObjects product2productMatrix,
                                            final int setSize,
                                            final List<ProductOracle> productOracles) {

        Map<String, Map<String, Integer>> map_use = product2productMatrix.getMap_use();
        for (String product1 : map_use.keySet()) {
            ArrayList<String> listProducts = new ArrayList<String>(map_use.get(product1).keySet());
            for (String product2 : listProducts) {
                Double commonoccurence = (double) map_use.get(product1).get(product2);
                Double totalOccurence = (double) (setSize - productUserMatrix.getRowCount(product1) - productUserMatrix.getRowCount(product2) + commonoccurence);
                Double score = calculateJaccardIndex(commonoccurence, totalOccurence);
                ProductOracle productOracle = ProductOracle.builder().productId1(product1).productId2(product2).prodCombIdx(ProductOracle.getIndex(product1, product2))
                    .score(score).build();
                productOracles.add(productOracle);
            }
        }
    }

    /**
     * Creates the product2 product matrix.
     * 
     * @param userProductMatrix the user product matrix
     * @param product2productMatrix the product2product matrix
     */
    private void createProduct2ProductMatrix(final MatrixOfObjects userProductMatrix, final MatrixOfObjects product2productMatrix) {

        Map<String, Map<String, Integer>> userProdMap = userProductMatrix.getMap_use();
        for (String user : userProdMap.keySet()) {
            ArrayList<String> productMap = new ArrayList<String>(userProdMap.get(user).keySet());
            Collections.sort(productMap);
            for (String product1 : productMap) {
                for (String product2 : productMap) {
                    if (product1.equals(product2))
                        continue;
                    int quant = product2productMatrix.getCellContents(product1, product2);
                    product2productMatrix.setCellContents(quant + 1, product1, product2);
                }
            }
        }
    }

    /**
     * Populate user prod matrix.
     * 
     * @param productUserMatrix the product user matrix
     * @param userProductMatrix the user product matrix
     */
    private void populateUserProdMatrix(final MatrixOfObjects productUserMatrix, final MatrixOfObjects userProductMatrix) {

        for (UserTransaction userTrans : transactions) {
            Integer quant = 0;
            quant = productUserMatrix.getCellContents(userTrans.getProductId(), userTrans.getUserId());
            productUserMatrix.setCellContents((quant != null ? quant : 0) + 1, userTrans.getProductId(), userTrans.getUserId());
            quant = userProductMatrix.getCellContents(userTrans.getUserId(), userTrans.getProductId());
            userProductMatrix.setCellContents((quant != null ? quant : 0) + 1, userTrans.getUserId(), userTrans.getProductId());
        }
    }

    /**
     * Rank.
     * 
     * @return
     */
    public Iterable<ProductOracle> rank() {

        // get all transactions
        transactions = Lists.newArrayList(userTransactionRepository.findAll());

        // populate user matrix
        MatrixOfObjects productUserMatrix = new MatrixOfObjects();
        MatrixOfObjects userProductMatrix = new MatrixOfObjects();
        populateUserProdMatrix(productUserMatrix, userProductMatrix);
        log.debug("created user2product and product2user index");
        // populate product matrix
        MatrixOfObjects product2productMatrix = new MatrixOfObjects();
        createProduct2ProductMatrix(userProductMatrix, product2productMatrix);
        log.debug("created product 2 product index");
        // rank products
        List<ProductOracle> productOracles = new ArrayList<>();
        createProduct2ProductIndex(productUserMatrix, product2productMatrix, transactions.size(), productOracles);
        return productOracleRepository.save(productOracles);

    }

}
