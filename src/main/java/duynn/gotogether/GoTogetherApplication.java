package duynn.gotogether;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
@EntityScan(basePackages = {"duynn.gotogether.entity"})  // force scan JPA entities
public class GoTogetherApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoTogetherApplication.class, args);
	}

}
