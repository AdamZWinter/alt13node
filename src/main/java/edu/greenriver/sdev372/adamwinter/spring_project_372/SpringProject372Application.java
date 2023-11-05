package edu.greenriver.sdev372.adamwinter.spring_project_372;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.*;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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

	/**
	 * Main entry point into the Spring Boot application
	 * @param args not used
	 */
	public static void main(String[] args) {

		//SpringApplication.run(SpringProject372Application.class, args);

		//ApplicationContext context = SpringApplication.run(SpringProject372Application.class, args);
		//IAccountsRepository repo = context.getBean(IAccountsRepository.class);

		//The following lines are required to initialize the blockchain and inject dependencies
		ApplicationContext context = SpringApplication.run(SpringProject372Application.class, args);
		InMemoryService inMemoryService = context.getBean(InMemoryService.class);
		inMemoryService.setAccountSet(new HashSet<>());
		inMemoryService.setBlockTime(BLOCK_TIME);

		long unixTimestamp = Instant.now().getEpochSecond();
		//long startTime = unixTimestamp - (unixTimestamp % BLOCK_TIME);
		//long endTime = startTime + BLOCK_TIME - 1;
		long startTime = unixTimestamp;
		long endTime = unixTimestamp;
		Block genesisBlock = new Block(startTime, endTime, "Genesis");

		//LinkedList is a doubly-linked list
		//and searches by index starting at either the head or the tail
		//depending on which the index is closer to
		inMemoryService.setBlockChain(new BlockChain(new LinkedList<>()), genesisBlock);


		//All following lines are for testing purposes only

		String user1 = "user1@email.com";
		String user2 = "user2@email.com";
		String user3 = "user3@email.com";

		try {
			inMemoryService.addAccount(new Account(user1, "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account(user2, "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account(user3, "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account("user4@gmail.com", "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account("user5@email.com", "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account("user6@email.com", "publicKeyPlaceholder"));
			inMemoryService.addAccount(new Account("user7@email.com", "publicKeyPlaceholder"));
			inMemoryService.saveAllAccountsToDatabase();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println(inMemoryService.getAccountByEmail(user1));

		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep((i+2)*1000 + i * 10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			startTime = Instant.now().getEpochSecond();
			SimpleTransaction simpleTransaction = new SimpleTransaction(
					user1,
					i+1,
					user2,
					0.42,
					startTime,
					"jsonEncodedObjectGoesHere",
					"SignaturePlaceholder"
			);

			SimpleTransaction simpleTransaction2 = new SimpleTransaction(
					user2,
					i+1,
					user3,
					0.42,
					startTime,
					"jsonEncodedObjectGoesHere",
					"SignaturePlaceholder"
			);

			SimpleTransaction simpleTransaction3 = new SimpleTransaction(
					user2,
					i+2,
					user3,
					0.42,
					startTime,
					"jsonEncodedObjectGoesHere",
					"SignaturePlaceholder"
			);
			inMemoryService.addTransaction(simpleTransaction);
			inMemoryService.addTransaction(simpleTransaction2);
			inMemoryService.addTransaction(simpleTransaction3);

			HashSet<ITransaction> transactionSet = (HashSet<ITransaction>) inMemoryService.getAllTransactions();
			System.out.println("Transaction set size: " + transactionSet.size());

		}//end for

	}

}
