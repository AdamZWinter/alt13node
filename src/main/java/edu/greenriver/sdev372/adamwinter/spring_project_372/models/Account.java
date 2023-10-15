package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for user data
 * @author Adam Winter
 * @version see version control
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements IAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String publicKey;
    private double balance;

    /**
     * Represents a user account
     * @param email an email address for the user
     */
    public Account(String email) {
        this.email = email;
        balance = 100.00;
    }
}
