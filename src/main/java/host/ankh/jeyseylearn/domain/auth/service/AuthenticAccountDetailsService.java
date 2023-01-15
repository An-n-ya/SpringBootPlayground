package host.ankh.jeyseylearn.domain.auth.service;

import host.ankh.jeyseylearn.domain.auth.AuthenticAccountRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Named
@Slf4j
public class AuthenticAccountDetailsService implements UserDetailsService {
    @Inject
    AuthenticAccountRepository authenticAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         PasswordEncoder encoder =
//         PasswordEncoderFactories.createDelegatingPasswordEncoder();
//         // 在这里算一下user的加密编码，编码每次都不一样，但是只需要取一种编码就可以了
//         // 另一种计算编码的方式是使用 spring cli：  `spring encodepassword user`
//         String encodedPassword = encoder.encode("user");
//
//         log.debug("encoded password: " + encodedPassword);
//         log.debug("query username = " + username);

        // 调用repository的实现，返回UserDetails实例
         var res = authenticAccountRepository.findByUsername(username);
         log.debug(res.toString());
        return res;
    }
}
