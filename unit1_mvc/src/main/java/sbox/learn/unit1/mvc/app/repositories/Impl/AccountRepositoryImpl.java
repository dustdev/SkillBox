package sbox.learn.unit1.mvc.app.repositories.Impl;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sbox.learn.unit1.mvc.app.entities.Account;
import sbox.learn.unit1.mvc.app.repositories.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Repository
@Log4j
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> repo = new ArrayList<>();

    @Override
    public List<Account> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Account account) {
        log.info("store new account: " + account);
        repo.add(account);
    }

    @Override
    public boolean remove(Predicate<Account> predicate) {
        log.info("removing accounts... ");
        return repo.removeIf(predicate);
    }

    @Override
    public List<Account> find(Predicate<Account> predicate) {
        log.info("search accounts... ");
        return repo.stream().filter(predicate).collect(Collectors.toList());
    }
}
