package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class SimpleTransaction implements ITransaction{

    private String accountId;
    private int transactionId;
    private String recipientId;
    private double amount;
    private int uTime;
    private String extra;
    private String base64encodedSignature;

    @Getter
    @Setter
    private int blockId;  //This will be -1 until the block is added to the chain

    public SimpleTransaction(String accountId, int transactionId, String recipientId, double amount, int uTime, String extra, String base64encodedSignature) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.uTime = uTime;
        this.extra = extra;
        this.base64encodedSignature = base64encodedSignature;
        this.blockId = -1;
    }

    @Override
    public String toString() {
        return "SimpleTransaction{" +
                "accountId='" + accountId + '\'' +
                ", transactionId=" + transactionId +
                ", recipientId='" + recipientId + '\'' +
                ", amount=" + amount +
                ", uTime=" + uTime +
                ", extra='" + extra + '\'' +
                ", base64encodedSignature='" + base64encodedSignature + '\'' +
                ", blockId=" + blockId +
                '}';
    }
}
