package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 *  A Block is a set of transactions that is appended to the blockchain
 */
public class Block implements IBlock{

    Set<ITransaction> transactionSet;
    long startTime;

    @Getter
    long endTime;
    String previousBlockHash;

    @Override
    public String toString() {
        return "Block{" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", previousBlock='" + previousBlockHash + '\'' +
                ", blockId=" + blockId +
                '}';
    }

    @Getter
    @Setter
    long blockId;  //this will be -1 until the block is added to the chain

    /**
     * Constructor
     * @param startTime The temporal beginning of the blcok
     * @param endTime  The temporal end of the block
     * @param previousBlockHash  The SHA256 hash of the toString of the previous block
     */
    public Block(long startTime, long endTime, String previousBlockHash) {
        this.transactionSet = new HashSet<>();
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousBlockHash = previousBlockHash;
        this.blockId = -1;
    }

    /**
     * Adds a transaction to the block
     * @param transaction type ITransaction
     * @return
     */
    public boolean addTransaction(ITransaction transaction){
        return transactionSet.add(transaction);
    }

    /**
     * The SHA256 hash of the toString of the block
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getHash() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String thisBlockString = this.toString();
        byte[] hashBytes =digest.digest(thisBlockString.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(hashBytes);
    }

    /**
     * Get all the transactions in this block
     * @return Set<ITransaction> The set of transations in this block
     */
    public Set<ITransaction> getAllTransactions(){
        return transactionSet;
    }

}
