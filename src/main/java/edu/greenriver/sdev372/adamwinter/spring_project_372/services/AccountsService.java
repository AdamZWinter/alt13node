package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AccountsService {
    public String getEmail(){
        String email = new Account("fake").getEmail();
        return email;
    }
}
