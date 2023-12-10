package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.Message;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Transaction implements ITransaction
 * This will be the parent class for all transactions
 * This format will allow for many different types of transactions
 * All the details of the transaction, other than blockId and signature
 * will be contained within the body
 */
@NoArgsConstructor
@AllArgsConstructor
public class PostedTransaction implements ITransaction{

    @Getter
    @Setter
    private int blockId;
    @Getter
    @Setter
    private String bodyString;
    @Getter
    private String bodyHash;
    @Getter
    @Setter
    private String bodyHashPosted;
    @Getter
    @Setter
    private String hashType;
    @Getter
    @Setter
    private String signature;
    @Getter
    @Setter
    private String pki;
    @Getter
    @Setter
    private String transactionType;


    /**
     * Constructor:  The blockId will be added
     * after the transaction has been added to the chain
     * @param body this should be a JSON formated String will all the details
     * @param signature the signature of the sender on the transaction
     */
    public PostedTransaction(String body, String bodyHash, String signature, String transactionType) {
        this.bodyString = body;
        this.bodyHashPosted = bodyHash;
        this.signature = signature;
        this.transactionType = transactionType;
        this.blockId = 0;
    }

    public boolean verifyHash(){
        return bodyHashPosted.equals(this.getBodyHashString());
    }

    @Override
    public String toString() {
        return "{" +
                "blockId=" + blockId +
                ", hash='" + getBodyHashString() + '\'' +
                ", body='" + bodyString + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    public byte[] getBodyHash() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(this.bodyString.getBytes(StandardCharsets.UTF_8));
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBodyHashString(){
        byte[] bytes = this.getBodyHash();
        Base64.Encoder base64encdoer = Base64.getEncoder();
        String hash = base64encdoer.encodeToString(bytes);
        return hash;
    }
}
