package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Set;

public class Block implements IBlock{

    Set<ITransaction> transactionSet;
    long startTime;

    @Getter
    long endTime;
    String previousBlockHash;

    @Override
    public String toString() {
        return "Block{" +
                "transactionSet=" + transactionSet +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", previousBlock='" + previousBlockHash + '\'' +
                ", blockId=" + blockId +
                '}';
    }

    @Getter
    @Setter
    int blockId;  //this will be -1 until the block is added to the chain

    public Block(long startTime, long endTime, String previousBlockHash) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousBlockHash = previousBlockHash;
        this.blockId = -1;
    }

    public boolean addTransaction(ITransaction transaction){
        return transactionSet.add(transaction);
    }

    public String getHash() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String thisBlockString = this.toString();
        byte[] hashBytes =digest.digest(thisBlockString.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(hashBytes);
    }

    public Set<ITransaction> getAllTransactions(){
        return transactionSet;
    }

}
