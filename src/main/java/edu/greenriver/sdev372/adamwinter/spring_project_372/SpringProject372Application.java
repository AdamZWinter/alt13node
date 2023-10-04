package edu.greenriver.sdev372.adamwinter.spring_project_372;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IAccountsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringProject372Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringProject372Application.class, args);
		//ApplicationContext context = SpringApplication.run(SpringProject372Application.class, args);
		//IAccountsRepository repo = context.getBean(IAccountsRepository.class);
	}

}
