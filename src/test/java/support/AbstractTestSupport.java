package support;


import com.example.coffeelogger.bot.longpolling.persistance.adapter.CoffeeOrderAdapter;
import com.example.coffeelogger.bot.longpolling.persistance.repository.CoffeeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractTestSupport {
    @Autowired
    protected CoffeeOrderAdapter coffeeOrderAdapter;
    @Autowired
    protected CoffeeOrderRepository repository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
            .withReuse(true)
            .withDatabaseName("test-container-db");

    static class TestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl())
                    .and("spring.datasource.username=" + postgreSQLContainer.getUsername())
                    .and("spring.datasource.password=" + postgreSQLContainer.getPassword())
                    .and("spring.datasource.driver-class-name=" + postgreSQLContainer.getDriverClassName())
            .applyTo(applicationContext.getEnvironment());
        }
    }
}
