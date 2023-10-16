package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IRepository;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@Service
@NoArgsConstructor
public class InMemoryService {

    //private IAccountsRepository repo;

    @Setter
    private IBlockChain blockChain;

    @Setter
    private Set<IAccount> accountSet;

    @Setter
    private long blockTime;

    public void addTransaction(ITransaction transaction){
        long unixTimestamp = Instant.now().getEpochSecond();
        IBlock currentBlock = blockChain.getCurrentBlock();
        long currentEndTime = currentBlock.getEndTime();
        String previousHash;
        if(unixTimestamp > currentEndTime){
            System.out.println("Creating new block");
            try {
                previousHash = currentBlock.getHash();
                System.out.println(previousHash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            long nextStartTime = unixTimestamp;
            long nextEndTime = nextStartTime + blockTime - 1;
            IBlock nextBlock = new Block(nextStartTime, nextEndTime, previousHash);
            nextBlock.addTransaction(transaction);
            blockChain.addBlock(nextBlock);
        }else{
            System.out.println("Adding to existing block.");
            currentBlock.addTransaction(transaction);
        }
    }

    public void addAccount(IAccount account){
        accountSet.add(account);
    }

    public IAccount getAccountByEmail(String email) throws NoSuchElementException {
        for (IAccount account : accountSet) {
            if(account.getEmail().compareTo(email) == 0 ){
                return account;
            }
        }
        throw new NoSuchElementException("There is no account with this email address.");
    }

    public Set<ITransaction> getCurrentTransactions(){
        return blockChain.getCurrentBlock().getAllTransactions();
    }

    public Set<ITransaction> getAllTransactions(){
        List<IBlock> allBlocks = blockChain.getAllBlocks();
        Set<ITransaction> allTransactions = new HashSet<>();
        for (IBlock block : allBlocks) {
            System.out.println(block);
            for (ITransaction transaction : block.getAllTransactions()) {
                //System.out.println(transaction);
                allTransactions.add(transaction);
            }
        }
        return allTransactions;
    }

//    /**
//     * Service constructor for the bean
//     * @param repo  this will be instantiated automatically by the Spring Boot framework
//     */
//    public AccountsService(IRepository repo) {
//        this.repo = repo;
//    }

}
