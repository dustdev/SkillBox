package sbox.learn.unit1.mvc.app.services;

import lombok.extern.log4j.Log4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import sbox.learn.unit1.mvc.app.entities.Account;
import sbox.learn.unit1.mvc.app.entities.QAccount;
import sbox.learn.unit1.mvc.app.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import static sbox.learn.unit1.mvc.app.entities.QAccount.account;
/**
 * Управление пользователями
 */
@Service
@Log4j
public class LoginService {

    private final AccountRepository accountRepo;

    @Autowired
    public LoginService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public boolean authenticate(String username, String password) {
        log.info("try auth with username: " + username);
        var account = accountRepo.findById(username);
        if (account.isPresent()) {
            var passwordInDb = account.get().getPassword();
            if (password != null && password.equals(passwordInDb)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(Account account) {
        log.info("try auth with account-form: " + account);
        if (accountRepo.existsById(account.getUsername())) {
            return false;
        } else {
            accountRepo.save(account);
            return true;
        }
    }
}
