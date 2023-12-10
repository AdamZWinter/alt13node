package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for user data
 * @author Adam Winter
 * @version see version control
 */
@Entity
@Data
@NoArgsConstructor
public class Account implements IAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String publicKey;

    @Setter
    @Getter
    private double balance;

    /**
     * Represents a user account
     * @param email an email address for the user
     */
    public Account(String email, String publicKey) {
        this.email = email;
        this.publicKey = publicKey;
        balance = 100.00;
    }



    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode()) {
            return true;
        }
        if (!(o instanceof Account account)) {
            return false;
        }
        return getId() == account.getId() && Double.compare(getBalance(), account.getBalance()) == 0 && Objects.equals(getEmail(), account.getEmail()) && Objects.equals(getPublicKey(), account.getPublicKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPublicKey(), getBalance());
    }
}
