package sbox.learn.unit1.mvc.app.repositories;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;
import sbox.learn.unit1.mvc.app.entities.Account;

/**
 * Интерфейс репозитория работы с пользователями
 */
@Repository
public interface AccountRepository extends QuerydslJdbcRepository<Account, String> {
}