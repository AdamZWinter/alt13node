package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import edu.greenriver.sdev372.adamwinter.spring_project_372.services.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  This class declares and route to HTTP resources.
 *
 * @author Adam Winter
 * @version see version control
 */

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class AccountsAPI {

    private AccountsService service;

    @GetMapping(path = "email")
    public String accountEmail(){
        return service.getEmail();
    }
}
