package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction implements ITransaction
 * This will be the parent class for all transactions
 * This format will allow for many different types of transactions
 * All the details of the transaction, other than blockId and signature
 * will be contained within the body
 */
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements ITransaction{

    @Getter
    @Setter
    private int blockId;
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private String signature;

    /**
     * Constructor:  The blockId will be added
     * after the transaction has been added to the chain
     * @param body this should be a JSON formated String will all the details
     * @param signature the signature of the sender on the transaction
     */
    public Transaction(String body, String signature) {
        this.body = body;
        this.signature = signature;
        this.blockId = 0;
    }

    @Override
    public String toString() {
        return "{" +
                "blockId=" + blockId +
                ", body='" + body + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
