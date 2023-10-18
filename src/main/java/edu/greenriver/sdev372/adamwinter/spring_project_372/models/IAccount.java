package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

/**
 * The interface for an account
 */
public interface IAccount {
    String getEmail();
    void setPublicKey(String publicKey);

    /**
     * Get the public key of the account
     * @return String a base64encoded public key
     */
    String getPublicKey();
}
