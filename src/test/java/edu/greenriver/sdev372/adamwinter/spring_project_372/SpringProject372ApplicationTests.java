package edu.greenriver.sdev372.adamwinter.spring_project_372;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class SpringProject372ApplicationTests {

	//run tests
	@Test
	void contextLoads() {
	}

	@Test
	void accountTest(){
		Account account = new Account("emailAddress");
	}


}
