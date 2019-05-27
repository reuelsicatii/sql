package org.nimphus.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="org.nimphus.sql.repository")
@SpringBootApplication
public class SqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlApplication.class, args);
	}

}
