import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.couponclues.config.CouponCluesApplication;
import com.sapient.couponclues.model.ProductOracle;
import com.sapient.couponclues.model.UserTransaction;
import com.sapient.couponclues.repository.ProductOracleRepository;
import com.sapient.couponclues.repository.UserTransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CouponCluesApplication.class)
public class TestESConfig {
    @Autowired
    ProductOracleRepository productOracleRepository;

    @Autowired
    UserTransactionRepository transactionRepository;

    @Test
    public void insertIndexes() {

        transactionRepository.save(new UserTransaction("trans0001", "user0001", "prod0001", new Date(), 2));
    }

    @Test
    public void insertsample() {

        ProductOracle userProductOracle = new ProductOracle(ProductOracle.getIndex("dummy", "dummmy"), "dummy", "dummy", 0d);
        productOracleRepository.save(userProductOracle);
    }

}
