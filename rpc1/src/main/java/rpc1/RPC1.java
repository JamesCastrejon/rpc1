package rpc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages="rpc1")
@EnableJpaRepositories(basePackages="rpc1.repos")
public class RPC1 extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RPC1.class, args);
	}
}
