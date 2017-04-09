package nl.meng;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@SpringBootApplication
@Configuration
@ComponentScan(basePackages="nl.meng.service")
public class PaymentValidatorApplication {

}
