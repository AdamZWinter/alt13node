package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.*;
import edu.greenriver.sdev372.adamwinter.spring_project_372.services.InMemoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

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

    /**
     * defines a route to get all accounts
     * @return Account
     */
    @CrossOrigin
    @GetMapping(path = "accounts")
    public ResponseEntity<Object> getAccountAll(){
        try {
            return new ResponseEntity<>(service.getAccountsAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * defines a route to create an account with POST
     * @param account specified in request body in JSON format
     * @return ResponseEntity<Boolean> CREATED or CONFLICT status
     */
    @CrossOrigin
    @PostMapping(path = "accounts")
    public ResponseEntity<Object> createAccount(@RequestBody Account account){
        try {
            return new ResponseEntity<>(service.addAccount(account), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    /**
     * defines a route to PUT/Update the public key for an account
     * @param account specified in request body in JSON format
     * @return ResponseEntity<Boolean> HttpStatus 204 No_Content or NOT_FOUND
     */
    @CrossOrigin
    @PutMapping(path = "accounts")
    public ResponseEntity<Boolean> updateAccountArbitrary(@RequestBody Account account){
        try {
            return new ResponseEntity<>(service.updateAccountById(account), HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * defines a route to DELETE the public key for an account
     * @param account specified in request body in JSON format
     * @return ResponseEntity<Boolean> HttpStatus 204 No_Content or NOT_FOUND
     */
    @CrossOrigin
    @DeleteMapping(path = "accounts")
    public ResponseEntity<Boolean> deleteAccountArbitrary(@RequestBody Account account){
        try {
            return new ResponseEntity<>(service.deleteAccountUsingId(account), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Defines a route to GET a block by the Id
     * @param id the id of the block
     * @return ResponseEntity<IBlock> HttpStatus OK or NOT_FOUND
     */
    @GetMapping("blocks/{id}")
    public ResponseEntity<IBlock> getBlockById(@PathVariable int id){
        try {
            return new ResponseEntity<>(service.getBlockById(id),HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new Block(0, 0, "Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Defines a route to POST a new transaction
     * @param transaction the PostedSimpleTransaction included as requestbody in POST
     * @return ResponseEntity<Boolean> Http.Status CREATED or INTERNAL_SERVER_ERROR
     */
    @PostMapping("transactions")
    public ResponseEntity<Boolean> postTransaction(@RequestBody PostedSimpleTransaction transaction){
    //public ResponseEntity<Boolean> postTransaction(){
        try {
            return new ResponseEntity<>(service.postTransaction(transaction), HttpStatus.CREATED);
            //return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Defines a route to GET all transactions
     * @return ResponseEntity<Set<ITransaction>> Http.Status OK only
     */
    @GetMapping("transactions")
    public ResponseEntity<Set<ITransaction>> getAllTransactions(){
        return new ResponseEntity<>(service.getAllTransactions(),HttpStatus.OK);
    }

    /**
     * Defines a route to GET transactions in the active block being built
     * These are the new transactions in the block that has not been
     * added to the blockchain yet.
     * These transactions should have a blockId of zero
     * @return ResponseEntity<Set<ITransaction>> Http.Status OK or INTERNAL_SERVER_ERROR
     */
    @GetMapping("transactions/new")
    public ResponseEntity<Set<ITransaction>> getNewTransactions(){
        try {
            return new ResponseEntity<>(service.getNewTransactions(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new HashSet<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
