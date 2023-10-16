package edu.greenriver.sdev372.adamwinter.spring_project_372;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IAccountsRepository;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.*;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.AccountsService;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import jakarta.transaction.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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

		//Java documentation says that LinkedList is a doubly-linked list
		//and that it searches by index starting at either the head or the tail
		//depending on which the index is closer to
		inMemoryService.setBlockChain(new BlockChain(new LinkedList<>(), genesisBlock));


		System.out.println(inMemoryService.getAccountByEmail(user1));

		for (int i = 0; i < 100; i++) {
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
			//System.out.println(inMemoryService.getAccountByEmail(user1));
			//System.out.println(inMemoryService.getAccountByEmail(user2));
			//System.out.println(inMemoryService.getAccountByEmail(user3));

			try {
				Thread.sleep(11000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}//end for

	}

}
