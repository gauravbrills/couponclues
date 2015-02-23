import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.couponclues.config.CouponCluesApplication;
import com.sapient.couponclues.model.UserProductOracle;
import com.sapient.couponclues.repository.UserProductOracleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CouponCluesApplication.class)
public class TestESConfig {
    @Autowired
    UserProductOracleRepository userProductOracleRepository;

    @Test
    public void insertsample() {

        UserProductOracle userProductOracle = new UserProductOracle("1", "user1", "prod1", 0d);
        userProductOracleRepository.save(userProductOracle);
    }

}
