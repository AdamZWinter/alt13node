package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IAccountsRepository;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@Service
public class InMemoryService {

    private IAccountsRepository repo;
    private IBlockChain blockChain;

    @Setter
    private Set<Account> accountSet;

    @Setter
    private long blockTime;

    private IBlock activeBlock;

    public InMemoryService(IAccountsRepository repo) {
        this.repo = repo;
    }

    public void setBlockChain(IBlockChain blockChain, IBlock genesisBlock) {
        this.blockChain = blockChain;
        this.activeBlock = genesisBlock;
        Transaction transaction = new SimpleTransaction("Genesis", 1, "Genesis", 0.0, Instant.now().getEpochSecond(), "Hello world.");
        transaction.setBlockId(0);
        activeBlock.addTransaction(transaction);
    }

    public void addTransaction(ITransaction transaction){
        transaction.setBlockId(0);
        long unixTimestamp = Instant.now().getEpochSecond();
        long currentEndTime = activeBlock.getEndTime();
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

            for (ITransaction transaction2Apply : previousBlock.getAllTransactions()) {
                ITransactionBody transactionBody = transaction2Apply.getBody();
                if(transactionBody.getBodyType().equals("SIMPLE")){
                    SimpleTransactionBody body = (SimpleTransactionBody) transactionBody;
                    System.out.println("Type: SIMPLE " + body);
                    String accountId = body.getAccountId();
                    String recipient = body.getRecipientId();
                    double amount = body.getAmount();
                    for (Account account : this.accountSet) {
                        if(account.getEmail().equals(accountId)){
                            double balance = account.getBalance();
                            account.setBalance(roundDouble(balance - amount, 2));
                        }
                        if(account.getEmail().equals(recipient)){
                            double balance = account.getBalance();
                            account.setBalance(roundDouble(balance + amount, 2));
                        }
                    }
                }else{
                    System.out.println("No type match.  Type:  " + transactionBody.getBodyType());
                }
            }

        }else{
            //System.out.println("Adding to existing block.");
            activeBlock.addTransaction(transaction);
        }
    }

    public Account addAccount(Account account) throws Exception{
        for (IAccount existingAccount : accountSet) {
            if(existingAccount.getEmail().compareTo(account.getEmail()) == 0 ){
                throw new Exception("Account with this email already exists.");
            }
        }
        repo.save(account);
        accountSet.add(account);
        return account;
    }

    public IAccount getAccountByEmail(String email) throws NoSuchElementException {
        for (IAccount account : accountSet) {
            if(account.getEmail().compareTo(email) == 0 ){
                return account;
            }
        }
        throw new NoSuchElementException("There is no account with this email address.");
    }

    public Set<Account> getAccountsAll() throws Exception {
        return accountSet;
    }

    public Set<ITransaction> getNewTransactions(){
        return activeBlock.getAllTransactions();
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
            for (ITransaction transaction : activeBlock.getAllTransactions()) {
                //System.out.println(transaction);
                allTransactions.add(transaction);
            }
        }
        return allTransactions;
    }

    public boolean updatePublicKeyByEmail(IAccount account) throws NoSuchElementException{
        Iterator itr = accountSet.iterator();
        while(itr.hasNext()){
            IAccount nextAccount = (IAccount) itr.next();
            if(account.getEmail().compareTo(nextAccount.getEmail()) == 0){
                nextAccount.setPublicKey(account.getPublicKey());
                return true;
            }
        }
        throw new NoSuchElementException("No such account.");
    }

    public boolean updateAccountById(Account account) throws NoSuchElementException{
        Iterator itr = accountSet.iterator();
        while(itr.hasNext()){
            Account nextAccount = (Account) itr.next();
            if(account.getId() == nextAccount.getId()){
                nextAccount.setEmail(account.getEmail());
                nextAccount.setPublicKey(account.getPublicKey());
                nextAccount.setBalance(account.getBalance());
                repo.save(nextAccount);
                return true;
            }
        }
        throw new NoSuchElementException("No such account.");
    }

    public boolean deleteAccountUsingId(Account account) throws NoSuchElementException{
        try {
            repo.deleteById(account.getId());
        } catch (Exception e) {
            throw new NoSuchElementException("No such account.");
        }

        Set<Account> tempSet = new HashSet<>();
        Iterator<Account> itr = accountSet.iterator();
        while(itr.hasNext()){
            Account nextAccount = itr.next();
            if(account.getId() != nextAccount.getId()){
                tempSet.add(nextAccount);
            }
        }
        this.accountSet = tempSet;
        return true;
    }

    public IBlock getBlockById(int id) throws NoSuchElementException{
        try {
            return blockChain.getBlockbyId(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e);
        }
    }

    public boolean postTransaction(ITransaction transaction) throws RuntimeException{
        try {
            addTransaction(transaction);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllAccountsToDatabase(){
        repo.saveAll(accountSet);
    }

    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
