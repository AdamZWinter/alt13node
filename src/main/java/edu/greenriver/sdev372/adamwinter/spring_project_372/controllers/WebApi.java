package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Block;
import edu.greenriver.sdev372.adamwinter.spring_project_372.models.IBlock;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
    public ResponseEntity<String> getAccountByEmail(@PathVariable String email){
        try {
            return new ResponseEntity<>(service.getAccountByEmail(email).toString(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "accounts")
    public ResponseEntity<Boolean> createAccount(@RequestBody Account account){
        try {
            return new ResponseEntity<>(service.addAccount(account), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(path = "accounts")
    public ResponseEntity<Boolean> updatePublicKeyByEmail(@RequestBody Account account){
        try {
            return new ResponseEntity<>(service.updatePublicKeyByEmail(account), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("blocks/{id}")
    public ResponseEntity<IBlock> getBlockById(@PathVariable int id){
        try {
            return new ResponseEntity<>(service.getBlockById(id),HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new Block(0, 0, "Not Found"), HttpStatus.NOT_FOUND);
        }
    }


}
