package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;


public class SimpleTransaction extends Transaction{

//    @Getter
//    private String accountId;
//    private int transactionId;
//
//    @Getter
//    private String recipientId;
//    private double amount;
//
//    @Getter
//    private long uTime;
//    private String extra;
//    private String base64encodedSignature;

    @Getter
    @Setter
    private int blockId;  //This will be -1 until the block is added to the chain

    public SimpleTransaction(String accountId, int transactionId, String recipientId, double amount, long uTime, String extra, String base64encodedSignature) {
        super();
        Body body = new Body(accountId, transactionId, recipientId, amount, uTime, extra);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString;
        try {
            bodyString = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        super.setBody(bodyString);
        super.setSignature(base64encodedSignature);
//        this.accountId = accountId;
//        this.transactionId = transactionId;
//        this.recipientId = recipientId;
//        this.amount = amount;
//        this.uTime = uTime;
//        this.extra = extra;
//        this.base64encodedSignature = base64encodedSignature;
//        this.blockId = -1;

    }

    private class Body{
        @Getter
        @Setter
        private String accountId;
        @Getter
        @Setter
        private int transactionId;
        @Getter
        @Setter
        private String recipientId;
        @Getter
        @Setter
        private double amount;
        @Getter
        @Setter
        private long uTime;
        @Getter
        @Setter
        private String extra;

        Body(String accountId, int transactionId, String recipientId, double amount, long uTime, String extra){
            this.accountId = accountId;
            this.transactionId = transactionId;
            this.recipientId = recipientId;
            this.amount = amount;
            this.uTime = uTime;
            this.extra = extra;
        }

    }

}
