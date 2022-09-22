package duynn.gotogether.controller;

import duynn.gotogether.entity.Account;
import duynn.gotogether.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    AccountServiceImpl accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account data) {
        boolean check = accountService.login(data);
        if (check) {
            return ResponseEntity.ok(check);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Account>> findAll() throws Exception {
//        return accountService.findAll();
        return ResponseEntity.ok().body(accountService.findAll());
    }
}
