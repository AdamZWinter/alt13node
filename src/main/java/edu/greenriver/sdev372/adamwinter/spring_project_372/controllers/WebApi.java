package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import edu.greenriver.sdev372.adamwinter.spring_project_372.services.AccountsService;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
