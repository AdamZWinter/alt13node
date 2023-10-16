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
    private IBlockChain blockChain;

    @Setter
    private Set<IAccount> accountSet;

    @Setter
    private long blockTime;

    private IBlock activeBlock;


//    public void setBlockChain(IBlockChain blockChain) {
//        this.blockChain = blockChain;
//    }

    public void setBlockChain(IBlockChain blockChain, IBlock genesisBlock) {
        this.blockChain = blockChain;
        this.activeBlock = genesisBlock;
    }

    public void addTransaction(ITransaction transaction){
        long unixTimestamp = Instant.now().getEpochSecond();
        //IBlock currentBlock = blockChain.getCurrentBlock();
        //long currentEndTime = currentBlock.getEndTime();
        long currentEndTime = activeBlock.getEndTime();
        //String previousHash;
        IBlock previousBlock;
        if(unixTimestamp > currentEndTime){
            //System.out.println("Creating new block");
            previousBlock = activeBlock;
            long nextStartTime = unixTimestamp;
            long nextEndTime = nextStartTime + blockTime - 1;
            try {
                activeBlock = new Block(nextStartTime, nextEndTime, previousBlock.getHash());
                System.out.println(previousBlock.getHash());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            activeBlock.addTransaction(transaction);
            blockChain.addBlock(previousBlock);
        }else{
            //System.out.println("Adding to existing block.");
            activeBlock.addTransaction(transaction);
        }
    }

//    public void addTransaction(ITransaction transaction){
//        long unixTimestamp = Instant.now().getEpochSecond();
//        IBlock currentBlock = blockChain.getCurrentBlock();
//        long currentEndTime = currentBlock.getEndTime();
//        String previousHash;
//        if(unixTimestamp > currentEndTime){
//            //System.out.println("Creating new block");
//            try {
//                previousHash = currentBlock.getHash();
//                System.out.println(previousHash);
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException(e);
//            }
//            long nextStartTime = unixTimestamp;
//            long nextEndTime = nextStartTime + blockTime - 1;
//            IBlock nextBlock = new Block(nextStartTime, nextEndTime, previousHash);
//            nextBlock.addTransaction(transaction);
//            blockChain.addBlock(nextBlock);
//        }else{
//            //System.out.println("Adding to existing block.");
//            currentBlock.addTransaction(transaction);
//        }
//    }

    public boolean addAccount(IAccount account){
        return accountSet.add(account);
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

    public boolean updatePublicKeyByEmail(IAccount account){
        Iterator itr = accountSet.iterator();
        while(itr.hasNext()){
            IAccount nextAccount = (IAccount) itr.next();
            if(account.getEmail().compareTo(nextAccount.getEmail()) == 0){
                nextAccount.setPublicKey(account.getPublicKey());
                return true;
            }
        }
        return false;
    }

    public IBlock getBlockById(int id){
        return blockChain.getBlockbyId(id);
    }

}