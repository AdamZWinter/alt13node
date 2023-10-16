package edu.greenriver.sdev372.adamwinter.spring_project_372;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IAccountsRepository;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Block;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.BlockChain;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.SimpleTransaction;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.AccountsService;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * This the main entry point into the Spring Boot application
 * it is the standard default implementation created by the Spring Boot framework
 */
@SpringBootApplication
public class SpringProject372Application {
	private static final long BLOCK_TIME = 10; //seconds

	public static void main(String[] args) {

		//SpringApplication.run(SpringProject372Application.class, args);

		//ApplicationContext context = SpringApplication.run(SpringProject372Application.class, args);
		//IAccountsRepository repo = context.getBean(IAccountsRepository.class);

		//Testing without API
		ApplicationContext context = SpringApplication.run(SpringProject372Application.class, args);
		InMemoryService inMemoryService = context.getBean(InMemoryService.class);
		inMemoryService.setAccountSet(new HashSet<>());
		inMemoryService.setBlockTime(BLOCK_TIME);

		String user1 = "user1@email.com";
		String user2 = "user2@email.com";
		String user3 = "user3@email.com";

		inMemoryService.addAccount(new Account(user1, "publicKeyPlaceholder"));
		inMemoryService.addAccount(new Account(user2, "publicKeyPlaceholder"));
		inMemoryService.addAccount(new Account(user3, "publicKeyPlaceholder"));

		long unixTimestamp = Instant.now().getEpochSecond();
		long startTime = unixTimestamp - (unixTimestamp % BLOCK_TIME);
		long endTime = startTime + BLOCK_TIME - 1;
		Block genesisBlock = new Block(startTime, endTime, "Genesis");
		inMemoryService.setBlockChain(new BlockChain(new LinkedList<>(), genesisBlock));

		SimpleTransaction simpleTransaction = new SimpleTransaction(
				user1,
				1,
				user2,
				42.42,
				startTime,
				"jsonEncodedObjectGoesHere",
				"SignaturePlaceholder"
				);

		SimpleTransaction simpleTransaction2 = new SimpleTransaction(
				user2,
				1,
				user3,
				42.42,
				startTime,
				"jsonEncodedObjectGoesHere",
				"SignaturePlaceholder"
		);

		SimpleTransaction simpleTransaction3 = new SimpleTransaction(
				user2,
				2,
				user3,
				42.42,
				startTime,
				"jsonEncodedObjectGoesHere",
				"SignaturePlaceholder"
		);

		inMemoryService.addTransaction(simpleTransaction);
		inMemoryService.addTransaction(simpleTransaction2);
		inMemoryService.addTransaction(simpleTransaction3);

		System.out.println(inMemoryService.getAccountByEmail(user1));

		System.out.println(inMemoryService.getAllTransactions());

	}

}
