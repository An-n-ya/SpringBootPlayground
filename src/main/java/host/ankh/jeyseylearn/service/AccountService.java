package host.ankh.jeyseylearn.service;

import host.ankh.jeyseylearn.domain.account.Account;
import host.ankh.jeyseylearn.domain.account.AccountRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 对Account的操作的Service
 */
@Named
@Slf4j
public class AccountService {
    @Inject
    private AccountRepository accountRepository;

    // 在 SecurityConfig 文件中有PasswordEncoder的Bean，所以这里能够注入成功
    @Inject
    private PasswordEncoder passwordEncoder;

    /**
     * 创建一个用户，注意：密码需要加密存储
     */
    public void createAccount(Account account) {
        log.debug(account.toString());
        // 加密密码
        var pass = passwordEncoder.encode(account.getPassword());
        account.setPassword(pass);
        // 调用jpa存储到数据库
        accountRepository.save(account);
    }
}
