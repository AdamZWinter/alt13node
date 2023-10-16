package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

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
            try {
                previousHash = currentBlock.getHash();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            long nextStartTime = unixTimestamp;
            long nextEndTime = nextStartTime + blockTime - 1;
            IBlock nextBlock = new Block(nextStartTime, nextEndTime, previousHash);

        }else{
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
                System.out.println(transaction);
                allTransactions.add(transaction);
            }
        }
        return allTransactions;
    }


}
