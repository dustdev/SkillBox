package sbox.learn.unit1.mvc.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import sbox.learn.unit1.mvc.app.entities.Account;
import sbox.learn.unit1.mvc.app.repositories.GenericRepository;
import sbox.learn.unit1.mvc.common.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);
    private final GenericRepository<Account,String> accountRepo;

    @Autowired
    public LoginService(GenericRepository<Account,String> accountRepo) {
        this.accountRepo = accountRepo;
    }

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        if (accountRepo.retreiveAll().size()>0) {
            return accountRepo.retreiveAll().stream()
                    .anyMatch(a -> a.getUsername().equals(loginFrom.getUsername()) && a.getPassword().equals(loginFrom.getPassword()));
        } else {
            return loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("123");
        }
    }

    public boolean register(Account account) {
        logger.info("try auth with account-form: " + account);
        if (accountRepo.retreiveAll().stream()
                .anyMatch(a -> a.getUsername().equals(account.getUsername()))) {
            return false;
        } else {
            accountRepo.store(account);
            return true;
        }
    }
}
