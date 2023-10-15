package edu.greenriver.sdev372.adamwinter.spring_project_372.services;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.IAccount;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.IBlockChain;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
public class InMemoryService {

    @Setter
    private IBlockChain blockChain;


    @Setter
    private Set<IAccount> accountSet;

    public void addAccount(IAccount account){
        accountSet.add(account);
    }

    public IAccount getAccountByEmail(String email) throws NoSuchElementException {
        for (IAccount account : accountSet) {
            if(account.getEmail().compareTo(email) == 0 ){
                return account;
            }
        }
        throw new NoSuchElementException("There is no account with this email address.");
    }


}
