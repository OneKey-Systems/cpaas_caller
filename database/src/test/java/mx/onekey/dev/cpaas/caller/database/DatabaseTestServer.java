package mx.onekey.dev.cpaas.caller.database;

import mx.onekey.dev.cpaas.caller.database.services.AccessTokenServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DatabaseTestServer {

    public static void main(String[] args) {
        SpringApplication.run(AccessTokenServiceTest.class, args);
    }
}
