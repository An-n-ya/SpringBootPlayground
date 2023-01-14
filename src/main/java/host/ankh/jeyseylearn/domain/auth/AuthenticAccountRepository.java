package host.ankh.jeyseylearn.domain.auth;

import host.ankh.jeyseylearn.domain.account.Account;
import host.ankh.jeyseylearn.domain.account.AccountRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Named
@Slf4j
public class AuthenticAccountRepository {
    @Inject
    private AccountRepository accountRepository;

    public AuthenticAccount findByUsername(String username) {
        log.debug("username = " + username);
        log.info("User found with findAll():");
        log.info("--------------------------");
        for (Account account : accountRepository.findAll()) {
            log.info(account.toString());
        }
//        Account a = accountRepository.findByUsername("user").get();
//        log.info("a = " + a.toString());

        return new AuthenticAccount(accountRepository.findByUsername(username).get());
    }

    public AuthenticAccount findById(Integer id) {
        log.debug("id = " + String.valueOf(id));

        return new AuthenticAccount(accountRepository.findById(id).orElseThrow(() -> {
            log.debug("用户 " + id + " 不存在!");
            return new UsernameNotFoundException("用户" + id + "不存在");
        }));
    }

}
