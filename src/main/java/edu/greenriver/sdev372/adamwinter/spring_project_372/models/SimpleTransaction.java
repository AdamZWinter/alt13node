package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

/**
 * SimpleTransaction extends Transaction, which implement ITransaction
 * This is a basic transaction that transfers notes from one account to another
 */
public class SimpleTransaction extends Transaction{

    /**
     * Constructor
     * @param accountId  the email address of the sender
     * @param transactionId  Like a check number
     * @param recipientId  the email address of the recipient
     * @param amount  the amount to send
     * @param uTime  a time stamp set by the sender
     * @param extra  additional information to include
     */
    public SimpleTransaction(String accountId, int transactionId, String recipientId, double amount, long uTime, String extra) {
        super();
        super.setTransactionType("SIMPLE");
        SimpleTransactionBody body = new SimpleTransactionBody(accountId, transactionId, recipientId, amount, uTime, extra);
        super.setBody(body);
        //super.setSignature(base64encodedSignature);
    }

}
