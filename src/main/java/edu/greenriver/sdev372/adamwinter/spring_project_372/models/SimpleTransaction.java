package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleTransaction implements ITransaction{

    private String accountId;
    private int transactionId;
    private String recipientId;
    private double amount;
    private int uTime;
    private String extra;
    private String base64encodedSignature;


}
