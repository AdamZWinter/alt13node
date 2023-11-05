package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import jakarta.persistence.Entity;

/**
 * The interface for an account
 */
public interface IAccount {
    int getId();
    void setEmail(String email);
    String getEmail();
    void setPublicKey(String publicKey);

    /**
     * Get the public key of the account
     * @return String a base64encoded public key
     */
    String getPublicKey();

    void setBalance(double balance);
    double getBalance();
}
