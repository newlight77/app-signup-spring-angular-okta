package cucumber.config

import io.cucumber.java.Before
import com.newlight77.core.OktaAuthApplication
import org.apache.commons.logging.LogFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [OktaAuthApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SpringIntegrationTest {
    val logger = LogFactory.getLog(javaClass)!!

    @Before
    fun setup() {
        // This one is necessary in order to pull up the spring context
        logger.info("Started the application")
    }
}