package duynn.gotogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"duynn.gotogether.entity"})  // force scan JPA entities
public class GoTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoTogetherApplication.class, args);
	}

}
