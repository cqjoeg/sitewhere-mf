import com.sitewhere.measurefilter.mvc.dao.UserRepository;
import com.sitewhere.measurefilter.mvc.domain.AppUser;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by CQ on 2016/11/18.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UserRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){

    }

    @After
    public void teardown() {
    }


    public void findByUsernameShouldReturnUser() throws Exception{
//        this.entityManager.persist(new AppUser("aaa"));
        AppUser appUser = this.userRepository.findByUsername("aaa");
        assertThat(appUser.getUsername()).isEqualTo("aaa");
    }

}
