/**
 * 
 */
package com.sapient.couponclues.loader;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.couponclues.config.CouponCluesApplication;
import com.sapient.couponclues.model.UserTransaction;
import com.sapient.couponclues.predictor.ProductAffinityRanker;
import com.sapient.couponclues.repository.UserTransactionRepository;
import com.sapient.couponclues.transform.IMarshaller;

/**
 * @author grawat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CouponCluesApplication.class)
public class DataLoader {

    @Autowired
    UserTransactionRepository transactionRepository;

    @Autowired
    IMarshaller marshaller;

    @Autowired
    ProductAffinityRanker affinityRanker;

    @Test
    public void insertIndexes() {

        // Insert User Transactions
        InputStream is = getClass().getResourceAsStream("/data/transactions.json");
        List<UserTransaction> decodeList = marshaller.decodeList(is, UserTransaction.class);
        transactionRepository.save(decodeList);
    }

    @Test
    public void testProductRanker() {

        affinityRanker.rank();
    }
}
