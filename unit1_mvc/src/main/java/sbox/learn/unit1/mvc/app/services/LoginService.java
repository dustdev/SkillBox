package sbox.learn.unit1.mvc.app.services;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import sbox.learn.unit1.mvc.app.entities.Account;
import sbox.learn.unit1.mvc.app.repositories.GenericRepository;
import sbox.learn.unit1.mvc.common.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class LoginService {

    private final GenericRepository<Account,String> accountRepo;

    @Autowired
    public LoginService(GenericRepository<Account,String> accountRepo) {
        this.accountRepo = accountRepo;
    }

    public boolean authenticate(LoginForm loginFrom) {
        log.info("try auth with user-form: " + loginFrom);
        if (accountRepo.retreiveAll().size()>0) {
            return accountRepo.retreiveAll().stream()
                    .anyMatch(a -> a.getUsername().equals(loginFrom.getUsername()) && a.getPassword().equals(loginFrom.getPassword()));
        } else {
            return loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("123");
        }
    }

    public boolean register(Account account) {
        log.info("try auth with account-form: " + account);
        if (accountRepo.retreiveAll().stream()
                .anyMatch(a -> a.getUsername().equals(account.getUsername()))) {
            return false;
        } else {
            accountRepo.store(account);
            return true;
        }
    }
}
