package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableEurekaClient
@SpringBootApplication
@EnableAsync
@EnableRetry
public class EurekaChinaTelecomHNApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaChinaTelecomHNApplication.class, args);
	}
}
