package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

import edu.greenriver.sdev372.adamwinter.spring_project_372.db.IAccountsRepository;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for accessing account data
 * @author Adam Winter
 * @version see version control
 */
@Service
public class AccountsService {

    private IAccountsRepository repo;

    public AccountsService(IAccountsRepository repo) {
        this.repo = repo;
    }

    public String getEmail(){
        String email = new Account("fake").getEmail();
        return email;
    }
}
