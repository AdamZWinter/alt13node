package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.IBlock;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *  This class declares routes to HTTP resources.
 *
 * @author Adam Winter
 * @version see version control
 */
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class WebApi {

    private InMemoryService service;

    /**
     * defines a route to get an account by email
     * @param email the email address associated with the account
     * @return Account
     */
    @GetMapping(path = "accounts/{email}")
    public String getAccountByEmail(@PathVariable String email){
        return service.getAccountByEmail(email).toString();
    }

    @PostMapping(path = "accounts")
    public boolean createAccount(@RequestBody Account account){
        return service.addAccount(account);
    }

    @PutMapping(path = "accounts")
    public boolean updatePublicKeyByEmail(@RequestBody Account account){
        return service.updatePublicKeyByEmail(account);
    }

    @GetMapping("blocks/{id}")
    public IBlock getBlockById(@PathVariable int id){
        return service.getBlockById(id);
    }


}
